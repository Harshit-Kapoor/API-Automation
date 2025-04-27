package com.Exercises;

import io.restassured.path.json.JsonPath;

import java.util.*;

public class RestAssuredSortingExample {
    public static void main(String[] args) {
        String jsonResponse = "{ \"Students\": [ " +
                "{ \"id\": 1, \"name\": \"Amit Sharma\", \"score\": 85 }, " +
                "{ \"id\": 2, \"name\": \"Priya Verma\", \"score\": 92 }, " +
                "{ \"id\": 3, \"name\": \"Rahul Mehta\", \"score\": 78 }, " +
                "{ \"id\": 4, \"name\": \"Sneha Kapoor\", \"score\": 88 }, " +
                "{ \"id\": 5, \"name\": \"Amit Sharma\", \"score\": 92 }, " +
                "{ \"id\": 6, \"name\": \"Pooja Reddy\", \"score\": 78 }, " +
                "{ \"id\": 7, \"name\": \"Vikram Singh\", \"score\": 85 }, " +
                "{ \"id\": 8, \"name\": \"Rahul Mehta\", \"score\": 88 }, " +
                "{ \"id\": 9, \"name\": \"Neha Agarwal\", \"score\": 92 } " +
                "] }";

        /*

        JsonPath js = new JsonPath(jsonResponse);

        List<HashMap<String, Object>> list = js.getList("Students");

        // METHOD 1

        Collections.sort(list, (e1, e2) -> {
            if ((Integer) e1.get("score") == (Integer) e2.get("score")) {
                return ((String)(e1.get("name"))).compareTo((String) e2.get("name"));
            }

            return (Integer) e2.get("score") - (Integer) e1.get("score");
        });


        System.out.println(list);


        // METHOD 2

        Comparator<Map<String, Object>> comparator = Comparator.comparing( (Map<String, Object> e) -> (Integer) e.get("score")).reversed().thenComparing((Map<String, Object> e) -> (String) e.get("name"));

        Collections.sort(list, comparator);

        System.out.println(list);

*/












        JsonPath js = new JsonPath(jsonResponse);

        List<Map<String, Object>> students = js.getList("Students");

        System.out.println(students);

        students.sort((a, b) -> (Integer) a.get("score") - (Integer) b.get("score"));

        System.out.println(students);

        students.sort((a, b) -> {
            if ((Integer) a.get("score") == (Integer) b.get("score")) {
                return ((String) a.get("name")).compareTo(((String) b.get("name")));
            }
            return (Integer) b.get("score") - (Integer) a.get("score");
        });

        System.out.println(students);

        Comparator<Map<String, Object>> score1 = Comparator.comparing((Map<String, Object> x) -> (Integer) x.get("score"));

        students.sort(score1);

        System.out.println(students);

        Comparator<Map<String, Object>> score2 = Comparator.comparing((Map<String, Object> x) -> (Integer) x.get("score")).reversed()
                .thenComparing(x -> (String) x.get("name"));

        students.sort(score2);

        System.out.println(students);

    }
}