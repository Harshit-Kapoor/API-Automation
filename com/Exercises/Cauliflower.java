package com.Exercises;

import io.restassured.path.json.JsonPath;

import java.util.HashMap;
import java.util.List;

public class Cauliflower {

    public static void main(String[] args) {

        String jsonResponse = "{\n" +
                "\n" +
                "\"Name\": \"Vegetables\",\n" +
                "\n" +
                "\"Number\": \"0001-0123-\",\n" +
                "\n" +
                "\"Path\": \"/Grocery-Store/Vegetables\",\n" +
                "\n" +
                "\"Subcategories\": [\n" +
                "\n" +
                "{\n" +
                "\n" +
                "\"Name\": \"Broccoli\",\n" +
                "\n" +
                "\"Number\": \"0001-0123-0456-\",\n" +
                "\n" +
                "\"Path\": \"/Grocery-Store/Vegetables/Broccoli\",\n" +
                "\n" +
                "\"HasClassifieds\": true,\n" +
                "\n" +
                "\"AreaOfBusiness\": 3,\n" +
                "\n" +
                "\"IsLeaf\": true\n" +
                "\n" +
                "},\n" +
                "\n" +
                "{\n" +
                "\n" +
                "\"Name\": \"Cauliflower\",\n" +
                "\n" +
                "\"Number\": \"0001-0123-0457-\",\n" +
                "\n" +
                "\"Path\": \"/Grocery-Store/Vegetables/Cauliflower\",\n" +
                "\n" +
                "\"Count\": 1,\n" +
                "\n" +
                "\"HasClassifieds\": true,\n" +
                "\n" +
                "\"AreaOfBusiness\": 3,\n" +
                "\n" +
                "\"IsLeaf\": true\n" +
                "\n" +
                "},\n" +
                "\n" +
                "{\n" +
                "\n" +
                "\"Name\": \"Potato\",\n" +
                "\n" +
                "\"Number\": \"0001-0123-0458-\",\n" +
                "\n" +
                "\"Path\": \"/Grocery-Store/Vegetables/Potato\",\n" +
                "\n" +
                "\"HasClassifieds\": true,\n" +
                "\n" +
                "\"AreaOfBusiness\": 3,\n" +
                "\n" +
                "\"IsLeaf\": true\n" +
                "\n" +
                "},\n" +
                "\n" +
                "\n" +
                "],\n" +
                "\n" +
                "\"Count\": 4,\n" +
                "\n" +
                "\"HasClassifieds\": true,\n" +
                "\n" +
                "\"AreaOfBusiness\": 3,\n" +
                "\n" +
                "\"IsLeaf\": false\n" +
                "\n" +
                "}";


        JsonPath js = new JsonPath(jsonResponse);

        List<HashMap<Object, Object>> list = js.getList("Subcategories");

        System.out.println("No of subcategories - " + list.size());

        for (int i = 0; i < list.size(); i++) {

            HashMap<Object, Object> map = list.get(i);

            if (map.get("Name").equals("Cauliflower")) {
                System.out.println(map.get("Count"));
            }

        }


    }
}
