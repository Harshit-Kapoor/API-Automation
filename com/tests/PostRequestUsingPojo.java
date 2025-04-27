package com.tests;

import com.pojo.Employee;
import com.pojo.FavouriteFoods;
import com.pojo.Marks;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.*;


public class PostRequestUsingPojo {

    // Plain Old Java Object

    // 1. for every {} we need to create a class
    // 2. for every [] we need to create List<>

    @Test
    public void pojoTest() {

        Marks marks1 = new Marks(24, 30);
        Marks marks2 = new Marks(27, 29);
        List<Marks> listOfMarks = Arrays.asList(marks1, marks2);

        FavouriteFoods favouriteFoods = new FavouriteFoods("cereal", "fruits",  Arrays.asList("rice", "beans"));

        Employee employee = new Employee(47, "Agent", " ",
                "bart.baker@gmail.com", favouriteFoods, Arrays.asList("player", "singer"), listOfMarks);

        Response response = given().header("Content-Type", ContentType.JSON)
                .log().all()
                .body(employee)
                .post("https://ca33825eff88d4eb1298.free.beeceptor.com/api/users/");

        response.prettyPrint();

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.header("Content-Type"), "application/json");
        System.out.println(response.jsonPath().getString("firstName"));
        List<String> jobs = response.jsonPath().getList("jobs");
        System.out.println(jobs);

        Employee deserializedEmp = response.as(Employee.class);
        System.out.println(deserializedEmp.getEmail());

        response.then().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schema.json"));

    }
}
