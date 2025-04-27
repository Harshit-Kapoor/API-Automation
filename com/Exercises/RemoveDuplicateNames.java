package com.Exercises;

import java.util.*;
import io.restassured.path.json.JsonPath;

public class RemoveDuplicateNames {

    public static void main(String[] args) {

        String jsonResponse = "{ \"Employees\": [ " +
                "{ \"id\": 101, \"name\": \"John Doe\", \"salary\": 50000 }, " +
                "{ \"id\": 102, \"name\": \"Alice Johnson\", \"salary\": 75000 }, " +
                "{ \"id\": 103, \"name\": \"Bob Smith\", \"salary\": 60000 }, " +
                "{ \"id\": 104, \"name\": \"Alice Johnson\", \"salary\": 80000 }, " +
                "{ \"id\": 105, \"name\": \"John Doe\", \"salary\": 55000 } " +
                "] }";

        JsonPath js = new JsonPath(jsonResponse);

        List<Map<String, Object>> list = js.getList("Employees");

        System.out.println(list);

        Set<String> names = new HashSet<>();

        List<Map<String, Object>> result = new ArrayList<>();

        for (Map<String, Object> map : list) {
            if (!names.contains((String) map.get("name"))) {
                names.add((String) map.get("name"));
                result.add(map);
            }
        }

        System.out.println(result);

    }
}
