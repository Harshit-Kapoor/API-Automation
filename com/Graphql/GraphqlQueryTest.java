package com.Graphql;

import com.Graphql.pojos.GraphqlQuery;
import com.Graphql.pojos.QueryVariable;
import io.restassured.RestAssured;
import org.codehaus.groovy.ast.Variable;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GraphqlQueryTest {

    @Test
    public void getAllFilmsTests() {

        // https://graphql.org/graphql/

        RestAssured.baseURI = "https://graphql.org/";
        String query = "{\"query\":\"{\\n  allFilms {\\n    films {\\n      title\\n      director\\n    }\\n  }\\n}\\n\"}";

        given().log().all()
                .contentType("application/json")
                .body(query)
                .when().log().all()
                .post("/graphql/")
                .then().log().all()
                .assertThat()
                .statusCode(200)
                .and()
                .body("data.allFilms.films[0].title", equalTo("A New Hope"));

    }

    @Test
    public void getAllUsers() {

        RestAssured.baseURI = "https://hasura.io/";
        String query = "{\"query\":\"{\\n  users {\\n    id\\n    name\\n  }\\n}\\n\",\"variables\":null}";

        given().log().all()
                .contentType("application/json")
                .header("Authorization", "Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6Ik9FWTJSVGM1UlVOR05qSXhSRUV5TURJNFFUWXdNekZETWtReU1EQXdSVUV4UVVRM05EazFNQSJ9.eyJodHRwczovL2hhc3VyYS5pby9qd3QvY2xhaW1zIjp7IngtaGFzdXJhLWRlZmF1bHQtcm9sZSI6InVzZXIiLCJ4LWhhc3VyYS1hbGxvd2VkLXJvbGVzIjpbInVzZXIiXSwieC1oYXN1cmEtdXNlci1pZCI6ImF1dGgwfDY4MGU0N2E2NjQ0ZWQ4YTkzOGEyODFjYyJ9LCJuaWNrbmFtZSI6ImhhcnNoaXRrMTgwMyIsIm5hbWUiOiJoYXJzaGl0azE4MDNAZ21haWwuY29tIiwicGljdHVyZSI6Imh0dHBzOi8vcy5ncmF2YXRhci5jb20vYXZhdGFyLzQ4N2E4MzE4YWExNDc1ZjU3YWYwNGU0ZWZmZjA2MTJiP3M9NDgwJnI9cGcmZD1odHRwcyUzQSUyRiUyRmNkbi5hdXRoMC5jb20lMkZhdmF0YXJzJTJGaGEucG5nIiwidXBkYXRlZF9hdCI6IjIwMjUtMDQtMjdUMTU6MDU6MTAuOTA3WiIsImlzcyI6Imh0dHBzOi8vZ3JhcGhxbC10dXRvcmlhbHMuYXV0aDAuY29tLyIsImF1ZCI6IlAzOHFuRm8xbEZBUUpyemt1bi0td0V6cWxqVk5HY1dXIiwic3ViIjoiYXV0aDB8NjgwZTQ3YTY2NDRlZDhhOTM4YTI4MWNjIiwiaWF0IjoxNzQ1NzY2MzEyLCJleHAiOjE3NDU4MDIzMTIsInNpZCI6Ik5hcWxfN3owNHlPTXh4WXVGcy1wNmtGLWJMa0FwdEF0IiwiYXRfaGFzaCI6IkRvcnBpV3I2MzBMczRFQ3RDNWJFWEEiLCJub25jZSI6IjFSLXVObVhLd21abTZoOW1HWHN0akY1U355RGRtc2l4In0.dozU02cJITiQSUFGmvfcGraT7QIWveP5ytN5EnbBVRN0X_EeEsdcTHEfVF4jDQTlro5141A0lrYOsrVHi0HF_N2Mn8qTjoFZFU8vdPuEu5AD9c5mGg_tY9drarEYHGWB6rD2Pc4rulBG6BAUUxEilmrU8CGxF0vSG2NUrmrt6NRxyxnL9SKEk6IMIqMKE33hyI1s3S8xn5_NVqBUebYHuuVRKwewAWmmx0k1cO1cKbVCQPAkoWE5Yvy4snCYvbyMeOxOXBxIc1Lwho4FmTB97oe_rdjuzhRl9mcJYmuaqgKqQlIgEkDRF25owpsFhM2h_XZ7F_AN4jG46txAWngIrQ")
                .body(query)
                .when().log().all()
                .post("learn/graphql")
                .then().log().all()
                .assertThat()
                .statusCode(200)
                .body("data.users[0].id", equalTo("auth0|5cc0ea100e618b11b031bb99"))
                .body("data.users[0].name", equalTo("tui.glen"));

    }


    @DataProvider
    public Object[][] limitData() {
        return new Object[][]{{"10", "tui.glen", "auth0|5cc0ea100e618b11b031bb99"}, {"5", "joscool.o2", "auth0|5cc0ef498e4be911a4f90c81"}};
    }

    @Test(dataProvider = "limitData")
    public void getAllUsersWithData(String limit, String name, String id) {

        RestAssured.baseURI = "https://hasura.io/";
        String query = "{\"query\":\"{\\n  users(limit: " + limit + ", where: {name: {_eq: \\\"" + name + "\\\"}}) {\\n    id\\n    name\\n  }\\n}\\n\",\"variables\":null}";

        given().log().all()
                .contentType("application/json")
                .header("Authorization", "Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6Ik9FWTJSVGM1UlVOR05qSXhSRUV5TURJNFFUWXdNekZETWtReU1EQXdSVUV4UVVRM05EazFNQSJ9.eyJodHRwczovL2hhc3VyYS5pby9qd3QvY2xhaW1zIjp7IngtaGFzdXJhLWRlZmF1bHQtcm9sZSI6InVzZXIiLCJ4LWhhc3VyYS1hbGxvd2VkLXJvbGVzIjpbInVzZXIiXSwieC1oYXN1cmEtdXNlci1pZCI6ImF1dGgwfDY4MGU0N2E2NjQ0ZWQ4YTkzOGEyODFjYyJ9LCJuaWNrbmFtZSI6ImhhcnNoaXRrMTgwMyIsIm5hbWUiOiJoYXJzaGl0azE4MDNAZ21haWwuY29tIiwicGljdHVyZSI6Imh0dHBzOi8vcy5ncmF2YXRhci5jb20vYXZhdGFyLzQ4N2E4MzE4YWExNDc1ZjU3YWYwNGU0ZWZmZjA2MTJiP3M9NDgwJnI9cGcmZD1odHRwcyUzQSUyRiUyRmNkbi5hdXRoMC5jb20lMkZhdmF0YXJzJTJGaGEucG5nIiwidXBkYXRlZF9hdCI6IjIwMjUtMDQtMjdUMTU6MDU6MTAuOTA3WiIsImlzcyI6Imh0dHBzOi8vZ3JhcGhxbC10dXRvcmlhbHMuYXV0aDAuY29tLyIsImF1ZCI6IlAzOHFuRm8xbEZBUUpyemt1bi0td0V6cWxqVk5HY1dXIiwic3ViIjoiYXV0aDB8NjgwZTQ3YTY2NDRlZDhhOTM4YTI4MWNjIiwiaWF0IjoxNzQ1NzY2MzEyLCJleHAiOjE3NDU4MDIzMTIsInNpZCI6Ik5hcWxfN3owNHlPTXh4WXVGcy1wNmtGLWJMa0FwdEF0IiwiYXRfaGFzaCI6IkRvcnBpV3I2MzBMczRFQ3RDNWJFWEEiLCJub25jZSI6IjFSLXVObVhLd21abTZoOW1HWHN0akY1U355RGRtc2l4In0.dozU02cJITiQSUFGmvfcGraT7QIWveP5ytN5EnbBVRN0X_EeEsdcTHEfVF4jDQTlro5141A0lrYOsrVHi0HF_N2Mn8qTjoFZFU8vdPuEu5AD9c5mGg_tY9drarEYHGWB6rD2Pc4rulBG6BAUUxEilmrU8CGxF0vSG2NUrmrt6NRxyxnL9SKEk6IMIqMKE33hyI1s3S8xn5_NVqBUebYHuuVRKwewAWmmx0k1cO1cKbVCQPAkoWE5Yvy4snCYvbyMeOxOXBxIc1Lwho4FmTB97oe_rdjuzhRl9mcJYmuaqgKqQlIgEkDRF25owpsFhM2h_XZ7F_AN4jG46txAWngIrQ")
                .body(query)
                .when().log().all()
                .post("learn/graphql")
                .then().log().all()
                .assertThat()
                .statusCode(200)
                .body("data.users[0].id", equalTo(id))
                .body("data.users[0].name", equalTo(name));

    }

    @Test()
    public void getTodoTitle() {
        RestAssured.baseURI = "https://hasura.io/";
        String query = "{\"query\":\"{\\n  users(limit: 10, where: {name: {_eq: \\\"akshayapsangi123\\\"}}) {\\n    id\\n    name\\n    todos(where: {title: {_eq: \\\"Flutter development\\\"}}) {\\n      title\\n    }\\n  }\\n}\\n\",\"variables\":null}";

        given().log().all()
                .contentType("application/json")
                .header("Authorization", "Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6Ik9FWTJSVGM1UlVOR05qSXhSRUV5TURJNFFUWXdNekZETWtReU1EQXdSVUV4UVVRM05EazFNQSJ9.eyJodHRwczovL2hhc3VyYS5pby9qd3QvY2xhaW1zIjp7IngtaGFzdXJhLWRlZmF1bHQtcm9sZSI6InVzZXIiLCJ4LWhhc3VyYS1hbGxvd2VkLXJvbGVzIjpbInVzZXIiXSwieC1oYXN1cmEtdXNlci1pZCI6ImF1dGgwfDY4MGU0N2E2NjQ0ZWQ4YTkzOGEyODFjYyJ9LCJuaWNrbmFtZSI6ImhhcnNoaXRrMTgwMyIsIm5hbWUiOiJoYXJzaGl0azE4MDNAZ21haWwuY29tIiwicGljdHVyZSI6Imh0dHBzOi8vcy5ncmF2YXRhci5jb20vYXZhdGFyLzQ4N2E4MzE4YWExNDc1ZjU3YWYwNGU0ZWZmZjA2MTJiP3M9NDgwJnI9cGcmZD1odHRwcyUzQSUyRiUyRmNkbi5hdXRoMC5jb20lMkZhdmF0YXJzJTJGaGEucG5nIiwidXBkYXRlZF9hdCI6IjIwMjUtMDQtMjdUMTU6MDU6MTAuOTA3WiIsImlzcyI6Imh0dHBzOi8vZ3JhcGhxbC10dXRvcmlhbHMuYXV0aDAuY29tLyIsImF1ZCI6IlAzOHFuRm8xbEZBUUpyemt1bi0td0V6cWxqVk5HY1dXIiwic3ViIjoiYXV0aDB8NjgwZTQ3YTY2NDRlZDhhOTM4YTI4MWNjIiwiaWF0IjoxNzQ1NzY2MzEyLCJleHAiOjE3NDU4MDIzMTIsInNpZCI6Ik5hcWxfN3owNHlPTXh4WXVGcy1wNmtGLWJMa0FwdEF0IiwiYXRfaGFzaCI6IkRvcnBpV3I2MzBMczRFQ3RDNWJFWEEiLCJub25jZSI6IjFSLXVObVhLd21abTZoOW1HWHN0akY1U355RGRtc2l4In0.dozU02cJITiQSUFGmvfcGraT7QIWveP5ytN5EnbBVRN0X_EeEsdcTHEfVF4jDQTlro5141A0lrYOsrVHi0HF_N2Mn8qTjoFZFU8vdPuEu5AD9c5mGg_tY9drarEYHGWB6rD2Pc4rulBG6BAUUxEilmrU8CGxF0vSG2NUrmrt6NRxyxnL9SKEk6IMIqMKE33hyI1s3S8xn5_NVqBUebYHuuVRKwewAWmmx0k1cO1cKbVCQPAkoWE5Yvy4snCYvbyMeOxOXBxIc1Lwho4FmTB97oe_rdjuzhRl9mcJYmuaqgKqQlIgEkDRF25owpsFhM2h_XZ7F_AN4jG46txAWngIrQ")
                .body(query)
                .when().log().all()
                .post("learn/graphql")
                .then().log().all()
                .assertThat()
                .statusCode(200)
                .body("data.users[0].id", equalTo("auth0|5cc324fa2093f00e26917f93"))
                .body("data.users[0].name", equalTo("akshayapsangi123"))
                .body("data.users[0].todos[0].title", equalTo("Flutter development"));

    }


    @Test
    public void getAllUserWithPojo() {
        RestAssured.baseURI = "https://hasura.io/";
        GraphqlQuery query = new GraphqlQuery();
        query.setQuery("query($limit:Int!){\n" +
                "  users(limit: $limit) {\n" +
                "    id\n" +
                "    name\n" +
                "  }\n" +
                "}\n");

        QueryVariable variable = new QueryVariable();
        variable.setLimit(10);

        query.setVariables(variable);

        given().log().all()
                .contentType("application/json")
                .header("Authorization", "Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6Ik9FWTJSVGM1UlVOR05qSXhSRUV5TURJNFFUWXdNekZETWtReU1EQXdSVUV4UVVRM05EazFNQSJ9.eyJodHRwczovL2hhc3VyYS5pby9qd3QvY2xhaW1zIjp7IngtaGFzdXJhLWRlZmF1bHQtcm9sZSI6InVzZXIiLCJ4LWhhc3VyYS1hbGxvd2VkLXJvbGVzIjpbInVzZXIiXSwieC1oYXN1cmEtdXNlci1pZCI6ImF1dGgwfDY4MGU0N2E2NjQ0ZWQ4YTkzOGEyODFjYyJ9LCJuaWNrbmFtZSI6ImhhcnNoaXRrMTgwMyIsIm5hbWUiOiJoYXJzaGl0azE4MDNAZ21haWwuY29tIiwicGljdHVyZSI6Imh0dHBzOi8vcy5ncmF2YXRhci5jb20vYXZhdGFyLzQ4N2E4MzE4YWExNDc1ZjU3YWYwNGU0ZWZmZjA2MTJiP3M9NDgwJnI9cGcmZD1odHRwcyUzQSUyRiUyRmNkbi5hdXRoMC5jb20lMkZhdmF0YXJzJTJGaGEucG5nIiwidXBkYXRlZF9hdCI6IjIwMjUtMDQtMjdUMTU6MDU6MTAuOTA3WiIsImlzcyI6Imh0dHBzOi8vZ3JhcGhxbC10dXRvcmlhbHMuYXV0aDAuY29tLyIsImF1ZCI6IlAzOHFuRm8xbEZBUUpyemt1bi0td0V6cWxqVk5HY1dXIiwic3ViIjoiYXV0aDB8NjgwZTQ3YTY2NDRlZDhhOTM4YTI4MWNjIiwiaWF0IjoxNzQ1NzY2MzEyLCJleHAiOjE3NDU4MDIzMTIsInNpZCI6Ik5hcWxfN3owNHlPTXh4WXVGcy1wNmtGLWJMa0FwdEF0IiwiYXRfaGFzaCI6IkRvcnBpV3I2MzBMczRFQ3RDNWJFWEEiLCJub25jZSI6IjFSLXVObVhLd21abTZoOW1HWHN0akY1U355RGRtc2l4In0.dozU02cJITiQSUFGmvfcGraT7QIWveP5ytN5EnbBVRN0X_EeEsdcTHEfVF4jDQTlro5141A0lrYOsrVHi0HF_N2Mn8qTjoFZFU8vdPuEu5AD9c5mGg_tY9drarEYHGWB6rD2Pc4rulBG6BAUUxEilmrU8CGxF0vSG2NUrmrt6NRxyxnL9SKEk6IMIqMKE33hyI1s3S8xn5_NVqBUebYHuuVRKwewAWmmx0k1cO1cKbVCQPAkoWE5Yvy4snCYvbyMeOxOXBxIc1Lwho4FmTB97oe_rdjuzhRl9mcJYmuaqgKqQlIgEkDRF25owpsFhM2h_XZ7F_AN4jG46txAWngIrQ")
                .body(query)
                .when().log().all()
                .post("learn/graphql")
                .then().log().all()
                .assertThat()
                .statusCode(200)
                .body("data.users[0].id", equalTo("auth0|5cc0ea100e618b11b031bb99"))
                .body("data.users[0].name", equalTo("tui.glen"));

    }

}
