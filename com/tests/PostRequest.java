package com.tests;

import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import static io.restassured.RestAssured.*;

public class PostRequest {

    @Test
    public void postTest() {

        // 1 passing json body as string in body for post call - not recommended for big json

        String body = "{\n" +
                "    \"email\": \"george.edwards@reqres.in\",\n" +
                "    \"first_name\": \"George\",\n" +
                "    \"last_name\": \"Edwards\",\n" +
                "    \"avatar\": \"https://reqres.in/img/faces/11-image.jpg\"\n" +
                "}";

        Response response = given()
                .header("Content-Type", "application/json")
//                .header("Content-Type", ContentType.JSON)
                .body(body)
                .log().all()
                .post("https://ca33825eff88d4eb1298.free.beeceptor.com/api/users/");

        response.prettyPrint();
        System.out.println(response.getStatusCode());

    }

    @Test
    public void postTest2() {

        // 2 pass from external file
        // cannot get request from file and print on console and static data can be used only

        Response response = given().header("Content-Type", ContentType.JSON)
                .log().all()
                .body(new File(System.getProperty("user.dir") + "/test.json"))
                .post("https://ca33825eff88d4eb1298.free.beeceptor.com/api/users/");

        response.prettyPrint();

    }


    @Test
    public void postTest3() throws IOException {

        // 2 pass from external file and read the request body and convert it to the string
        // can get request from file and print on console and static data can be used only
        // change few params as well

        byte[] bytes = Files.readAllBytes(Paths.get(System.getProperty("user.dir") + "/test.json"));
        String reqBody = new String(bytes);
        String replace = reqBody.replace("fname", new Faker().name().firstName());

        Response response = given().header("Content-Type", ContentType.JSON)
                .log().all()
                .body(replace)
                .post("https://ca33825eff88d4eb1298.free.beeceptor.com/api/users/");

        response.prettyPrint();


    }

    @Test
    public void postTest4() throws IOException {

        // using map and list interface
        // {} maps will be used
        // [] array will be used

        // serializers convert language specific objects to byte stream -> json, want to transfer piece of info to server = Serialization in the process

        Map<String, Object> obj = new HashMap<>();
        obj.put("email", new Faker().internet().emailAddress());
        obj.put("first_name", new Faker().name().firstName());
        obj.put("last_name", new Faker().name().lastName());
        obj.put("avatar", new Faker().internet().url());

        List<String> listOfJobs = new ArrayList<>();
        listOfJobs.add("tester");
        listOfJobs.add("player");

        obj.put("jobs", listOfJobs);

        Map<String, Object> food = new HashMap<>();
        food.put("breakfast", "omelette");
        food.put("lunch", "pasta");
        food.put("dinner", Arrays.asList("rice", "soup"));

        obj.put("favFoods", food);

        Map<String, Object> marks1 = new HashMap<>();

        marks1.put("english", 24);
        marks1.put("german", 30);

        Map<String, Object> marks2 = new HashMap<>();

        marks2.put("english", 29);
        marks2.put("german", 33);

        List<Map> listOfMarks = new ArrayList<>();

        listOfMarks.add(marks1);
        listOfMarks.add(marks2);

        obj.put("marks", listOfMarks);

        Response response = given().header("Content-Type", ContentType.JSON)
                .log().all()
                .body(obj)
                .post("https://ca33825eff88d4eb1298.free.beeceptor.com/api/users/");

        response.prettyPrint();


    }

    @Test
    public void test5() {

        // using external json library
        // {} -> JsonObject
        // benefit is we dont have to worry about object types of java or generics

        JSONObject obj = new JSONObject();
        obj.put("email", new Faker().internet().emailAddress());
        obj.accumulate("email", new Faker().internet().emailAddress());  // json library accumulates this for same key

        obj.append("email2", "testemail@gmail.com");   // will be in array if key is one
        obj.accumulate("email3", new Faker().internet().emailAddress());   // will be in string if key is only one

        //  Scenario - when we use only one key
        //  accumulate - will give string
        //  append - will give string in array

        obj.put("first_name", new Faker().name().firstName());
        obj.put("last_name", new Faker().name().lastName());
        obj.put("avatar", new Faker().internet().url());

        JSONArray listOfJobs = new JSONArray();
        listOfJobs.put("tester");
        listOfJobs.put("player");
        obj.put("jobs", listOfJobs);

        JSONObject food = new JSONObject();
        food.put("breakfast", "omelette");
        food.put("lunch", "pasta");

        JSONArray listOfFoods = new JSONArray();
        listOfFoods.put("rice");
        listOfFoods.put("peas");
        food.put("dinner", listOfFoods);

        obj.put("favFoods", food);

        System.out.println("************************************************");
        System.out.println(obj);

        Response response = given().header("Content-Type", ContentType.JSON)
                .log().all()
                .body(obj.toMap())   // jackson helps to serialize this
                .post("https://ca33825eff88d4eb1298.free.beeceptor.com/api/users/");

        response.prettyPrint();

    }

}
