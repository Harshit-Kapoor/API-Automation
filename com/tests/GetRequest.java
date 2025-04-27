package com.tests;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.*;

public class GetRequest {

    @Test
    public void getTest() {

        given().get("https://ca33825eff88d4eb1298.free.beeceptor.com/api/users").then().statusCode(200);

        Response response = given().get("https://ca33825eff88d4eb1298.free.beeceptor.com/api/users");
        response.prettyPrint();

        System.out.println(response.header("Content-Type"));
        Headers headers = response.headers();
        for (Header h: headers) {
            System.out.print(h.getName() + " : ");
            System.out.println(h.getValue());
        }

        System.out.println(response.getStatusCode());
        System.out.println(response.getTime());
        System.out.println(response.getTimeIn(TimeUnit.SECONDS));

    }

    @Test
    public void getTest1() throws IOException {

        Response response = given().log().all()
                .queryParam("id", 47).get("https://ca33825eff88d4eb1298.free.beeceptor.com/api/users");

        System.out.println(response.asString());

        Files.write(Paths.get(System.getProperty("user.dir") + "/response.json"), response.asByteArray());

    }

    @Test
    public void getTest2() {

        given().get("https://ca33825eff88d4eb1298.free.beeceptor.com/api/users").then().statusCode(200);

        Response response = given().get("https://ca33825eff88d4eb1298.free.beeceptor.com/api/users");
        response.prettyPrint();

        System.out.println(response.header("Content-Type"));
        Headers headers = response.headers();
        for (Header h: headers) {
            System.out.print(h.getName() + " : ");
            System.out.println(h.getValue());
        }

        System.out.println(response.getStatusCode());
        System.out.println(response.getTime());
        System.out.println(response.getTimeIn(TimeUnit.SECONDS));

    }



}
