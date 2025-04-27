package com.Exercises;

import io.restassured.path.json.JsonPath;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MoreThanTwoOrders {

    public static void main(String[] args) {

        String jsonResponse = "{\n" +
                "  \"Users\": [\n" +
                "    { \"id\": 1, \"name\": \"Alice\", \"orders\": 3 },\n" +
                "    { \"id\": 2, \"name\": \"Bob\", \"orders\": 1 },\n" +
                "    { \"id\": 3, \"name\": \"Charlie\", \"orders\": 5 },\n" +
                "    { \"id\": 4, \"name\": \"David\", \"orders\": 2 }\n" +
                "  ]\n" +
                "}";


        JsonPath js = new JsonPath(jsonResponse);

        List<Map<String, Object>> users = js.getList("Users");

        System.out.println(users);

        List<Map<String, Object>> result = new ArrayList<>();

        for (Map<String, Object> map : users) {
            if ((Integer) map.get("orders") > 2) {
                result.add(map);
            }
        }


        System.out.println(result);

        List<Map<String, Object>> filteredUsers = users.stream()
                .filter(user -> (Integer) user.get("orders") > 2)
                .collect(Collectors.toList());

        System.out.println(filteredUsers);

        users.stream()
                .filter(user -> (Integer) user.get("orders") > 2)
                .forEach(user ->
                        System.out.println("ID: " + user.get("id") + ", Name: " + user.get("name") + ", Orders: " + user.get("orders"))
                );


    }
}
