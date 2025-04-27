package com.Exercises;

import java.util.List;
import java.util.Map;

import io.restassured.path.json.JsonPath;

import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TopThreeSalaries {
    public static void main(String[] args) {
        String jsonResponse = "{ \"Employees\": [ " +
                "{ \"id\": 101, \"name\": \"John Doe\", \"salary\": 50000 }, " +
                "{ \"id\": 102, \"name\": \"Alice Johnson\", \"salary\": 75000 }, " +
                "{ \"id\": 103, \"name\": \"Bob Smith\", \"salary\": 60000 }, " +
                "{ \"id\": 104, \"name\": \"Charlie Brown\", \"salary\": 75000 }, " +
                "{ \"id\": 105, \"name\": \"David Wilson\", \"salary\": 50000 } " +
                "] }";


        JsonPath js = new JsonPath(jsonResponse);

        List<Map<String, Object>> employess = js.getList("Employees");

        List<Map<String, Object>> collect = employess.stream().sorted((a, b) -> (int) b.get("salary") - (int) a.get("salary")).limit(3).collect(Collectors.toList());

        System.out.println(collect);

        Comparator<Map<String, Object>> salary = Comparator
                .comparing((Map<String, Object> e) -> (Integer) e.get("salary"))
                .reversed()
                .thenComparing((Map<String, Object> x) -> (String) x.get("name"));

        employess.sort(Comparator.comparing((Map<String, Object> e) -> (Integer) e.get("salary")).reversed());
        System.out.println(employess);
        employess.sort(salary);
        System.out.println(employess);




        // Extract distinct top three salaries
        List<Integer> topSalaries = employess.stream()
                .map(emp -> (int) emp.get("salary")) // Extract salaries
                .distinct() // Remove duplicates
                .sorted(Comparator.reverseOrder()) // Sort in descending order
                .limit(3) // Get top 3 unique salaries
                .collect(Collectors.toList());

        System.out.println(topSalaries);

        // Filter employees having these top salaries
        List<Map<String, Object>> topEmployees = employess.stream()
                .filter(emp -> topSalaries.contains((int) emp.get("salary")))
                .collect(Collectors.toList());

        System.out.println("Top 3 Distinct Salaries:");
        for (Map<String, Object> emp : topEmployees) {
            System.out.println(emp.get("name") + " - " + emp.get("salary"));
        }

    }
}
