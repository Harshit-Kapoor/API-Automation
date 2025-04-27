package com.Exercises;

import io.restassured.path.json.JsonPath;

import java.util.List;
import java.util.Map;

public class TotalStockValue {

    public static void main(String[] args) {

        String jsonResponse = "{\n" +
                "  \"Items\": [\n" +
                "    { \"id\": 1, \"name\": \"Laptop\", \"price\": 800, \"stock\": 5 },\n" +
                "    { \"id\": 2, \"name\": \"Phone\", \"price\": 500, \"stock\": 10 },\n" +
                "    { \"id\": 3, \"name\": \"Tablet\", \"price\": 300, \"stock\": 8 }\n" +
                "  ]\n" +
                "}";

        JsonPath js = new JsonPath(jsonResponse);

        List<Map<String, Object>> list = js.getList("Items");

        int totalSum = 0;

        for (Map<String, Object> m : list) {

            int i = (Integer) m.get("price") * (Integer) m.get("stock");
            totalSum += i;

        }

        System.out.println(totalSum);
    }
}
