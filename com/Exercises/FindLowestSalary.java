package com.Exercises;

import io.restassured.path.json.JsonPath;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FindLowestSalary {
    public static void main(String[] args) {
        String jsonResponse = "{ \"Employees\": [ " +
                "{ \"id\": 101, \"name\": \"John Doe\", \"salary\": 50000 }, " +
                "{ \"id\": 102, \"name\": \"Alice Johnson\", \"salary\": 75000 }, " +
                "{ \"id\": 103, \"name\": \"Bob Smith\", \"salary\": 60000 }, " +
                "{ \"id\": 104, \"name\": \"Charlie Brown\", \"salary\": 45000 }, " +
                "{ \"id\": 105, \"name\": \"David Wilson\", \"salary\": 50000 } " +
                "] }";

        JsonPath js = new JsonPath(jsonResponse);

        List<Map<String, Object>> employees = js.getList("Employees");

        System.out.println(employees);

        // METHOD 1

        List<Map<String, Object>> result = new ArrayList<>();

        int minSalary = Integer.MAX_VALUE;

        for (Map<String, Object> map : employees) {
            if ((int) map.get("salary") < minSalary) {
                minSalary = (int) map.get("salary");
            }
        }

        for (Map<String, Object> map : employees) {
            if ((int) map.get("salary") == minSalary) {
                result.add(map);
            }
        }

        System.out.println(result);

        // METHOD 2

        List<Map<String, Object>> ascendingOrderedList = employees.stream().sorted((a, b) -> (int) a.get("salary") - (int) b.get("salary")).collect(Collectors.toList());

        System.out.println("Reversed Order list first element -> " + ascendingOrderedList.get(0).get("salary"));

        // METHOD 3

        Comparator<Map<String, Object>> comparator = Comparator.comparing((Map<String, Object> e) -> (Integer) e.get("salary"));

        employees.sort(comparator);

        System.out.println(employees.get(0).get("salary"));

    }
}
