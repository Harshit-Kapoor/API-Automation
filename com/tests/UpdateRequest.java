package com.tests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

public class UpdateRequest {

    @Test
    public void updateTest() {

        // put call with path param used

        JSONObject object = new JSONObject();
        object.put("firstName", "AgentNo");
        object.put("lastName", "Ghost");

        Response response = given().header("Content-Type", ContentType.JSON)
                .pathParam("id", 45)
                .log().all().body(object.toMap())
                .put("https://ca33825eff88d4eb1298.free.beeceptor.com/api/users/{id}");

    }

}
