package com.Programs;

import io.restassured.path.json.JsonPath;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class Programs {
    public static class Program {

    //    public static void main(String[] args) {
    //
    //        JsonPath js = new JsonPath();
    //
    //        List<Integer> scores = js.getList(“Students.score”);
    //
    //        List<Integer> names = js.getList(“Students.name”);
    //
    //        for (int i = 0; i<scores.size(); i++) {
    //            for (int j = 0; j < scores.size(); j++) {
    //                if (scores.get(j) > scores.get(j + 1)) {
    //                    int temp = scores.get(j);
    //                    scores.get(j) = scores.get(j + 1);
    //                    scores.get(j+1) = temp;
    //                }
    //            }
    //        }
    //
    //        ArrayList<ArrayList<Object>> al = new ArrayList<>();
    //
    //        for (int i = scores.size() - 1; i>=0; i--) {
    //
    //            ArrayList<Object> l = new ArrayList<>();
    //
    //            if (js.get("Students.score") == scores.get(i)) {
    //                l.add(js.get("Students.id"));
    //                l.add(js.get("Students.name"));
    //                l.add(js.get("Students.score"));
    //            }
    //
    //            al.add(l);
    //
    //        }
    //
    //        ArrayList<String> namesList = new ArrayList<>();
    //
    //        for (int i=0; i<al.size(); i++) {
    //
    //            namesList.add(al.get(i).get(1);
    //
    //        }
    //
    //
    //
    //    }

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

            // Parse JSON response
            JsonPath jsonPath = new JsonPath(jsonResponse);

            List<Map<String, Object>> students = jsonPath.getList("Students");

            // Sort by score (descending) and name (ascending)
            students.sort(Comparator.comparing((Map<String, Object> student) -> (Integer) student.get("score")).reversed()
                    .thenComparing(student -> (String) student.get("name")));

            // Print sorted list
            for (Map<String, Object> student : students) {
                System.out.println("ID: " + student.get("id") + ", Name: " + student.get("name") + ", Score: " + student.get("score"));
            }
        }

    }
}
