package com.Programs;

import io.restassured.path.json.JsonPath;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class Program2 {

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

        JsonPath jsonPath = new JsonPath(jsonResponse);

        List<Map<String, Object>> students = jsonPath.getList("Students");

        System.out.println(students);

        students.sort((s1, s2) -> {
            if (s1.get("score") == s2.get("score")) {
                return ((String) s1.get("name")).compareTo((String) s2.get("name"));
            }
            return (Integer) s2.get("score") - (Integer) s1.get("score");
        });

        System.out.println(students);

        for (Map<String, Object> student : students) {
            System.out.println("ID: " + student.get("id") + ", Name: " + student.get("name") + ", Score: " + student.get("score"));
        }

        System.out.println("Top 3 scores:");
        students.stream().limit(3).forEach(st ->
                System.out.println("ID: " + st.get("id") + ", Name: " + st.get("name") + ", Salary: " + st.get("salary"))
        );



        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        Comparator<Map<String, Object>> score = Comparator.comparing((Map<String, Object> s) -> (Integer) s.get("score"));

        Comparator<Map<String, Object>> scoreAndName = Comparator.comparing((Map<String, Object> student) -> (Integer) student.get("score")).reversed()
                .thenComparing(student -> (String) student.get("name"));

        students.sort(score);

        System.out.println(students);

        students.sort(scoreAndName);

        System.out.println(students);

        students.sort(Comparator.comparing((Map<String, Object> student) -> (Integer) student.get("score")).reversed()
                .thenComparing(student -> (String) student.get("name")));

    }

}
