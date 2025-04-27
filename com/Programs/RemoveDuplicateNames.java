package com.Programs;

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

        JsonPath jsonPath = new JsonPath(jsonResponse);
        List<Map<String, Object>> employees = jsonPath.getList("Employees");

        Set<String> seenNames = new HashSet<>();
        List<Map<String, Object>> uniqueEmployees = new ArrayList<>();

        for (Map<String, Object> emp : employees) {
            String name = (String) emp.get("name");
            if (!seenNames.contains(name)) {
                seenNames.add(name);
                uniqueEmployees.add(emp);
            }
        }

        System.out.println("Unique Employees:");
        for (Map<String, Object> emp : uniqueEmployees) {
            System.out.println("ID: " + emp.get("id") + ", Name: " + emp.get("name") + ", Salary: " + emp.get("salary"));
        }
    }
}
