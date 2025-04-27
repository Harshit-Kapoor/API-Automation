package com.Exercises;

import io.restassured.path.json.JsonPath;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class EarliestRegisteredUser {

    public static void main(String[] args) {

        String jsonResponse = "{\n" +
                "  \"Users\": [\n" +
                "    { \"id\": 1, \"name\": \"Alice\", \"registeredOn\": \"2023-07-10\" },\n" +
                "    { \"id\": 2, \"name\": \"Bob\", \"registeredOn\": \"2021-06-15\" },\n" +
                "    { \"id\": 3, \"name\": \"Charlie\", \"registeredOn\": \"2022-12-01\" }\n" +
                "  ]\n" +
                "}";

        JsonPath jsonPath = new JsonPath(jsonResponse);
        List<Map<String, Object>> users = jsonPath.getList("Users");

//        Map<String, Object> earliestUser = users.stream()
//                .min(Comparator.comparing(user -> (String) user.get("registeredOn")))
//                .orElse(null);
//
//        if (earliestUser != null) {
//            System.out.println("Earliest Registered User: ID: " + earliestUser.get("id") + ", Name: " + earliestUser.get("name") + ", Registered On: " + earliestUser.get("registeredOn"));
//        }


        // METHOD 2

//        Comparator<Map<String, Object>> comparator = Comparator.comparing((Map<String, Object> e) -> (String) e.get("registeredOn")).reversed();
//
//        Collections.sort(users, comparator);
//
//        System.out.println(users);


        // METHOD 3

        Collections.sort(users, (e1, e2) -> {
            return ((String) e2.get("registeredOn")).compareTo((String) e1.get("registeredOn"));
        });

        System.out.println(users);



    }
}
