package com.tests;

import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static io.restassured.RestAssured.given;

public class JsonPathValidations {

    @Test
    public void postTest3() throws IOException {

        String response = JsonTest.sampleJson2();
        JsonPath js = new JsonPath(response);

        System.out.println(js.getString("email"));
        System.out.println(js.getString("favouriteFoods"));
        System.out.println(js.getString("favouriteFoods.dinner[0]"));
        System.out.println(js.getString("favouriteFoods.dinner.size()"));

        System.out.println(js.getInt("marks.size()"));

        //System.out.println(js.get("marks.english[0]"));;

        int totalSum = 0;

        for (int i = 0; i < js.getInt("marks.size()"); i++) {

            totalSum += js.getInt("marks.english[" + i + "]");

        }

        System.out.println(totalSum);

        //System.out.println(js.get("marks.german"));;

        List<Integer> germanMarks = js.getList("marks.german");

        System.out.println(germanMarks);

        System.out.println(germanMarks.get(1));

        System.out.println(js.getInt("marks[0].english"));
        System.out.println(js.getInt("marks.english[0]"));

    }



}


