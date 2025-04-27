package com.tests;

import io.restassured.response.Response;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

public class DeleteRequest {

    @Test
    public void deleteRequest() {

        Response res = given().log().all()
                .log().all()
                .pathParam("id", 45)
                .delete("https://ca33825eff88d4eb1298.free.beeceptor.com/api/users/{id}");

    }

}
