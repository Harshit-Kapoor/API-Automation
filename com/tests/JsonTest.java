package com.tests;

public class JsonTest {

    public static String sampleJson() {

        return "{\n" +
                "  \"$schema\": \"http://json-schema.org/draft-04/schema#\",\n" +
                "  \"type\": \"object\",\n" +
                "  \"properties\": {\n" +
                "    \"email\": {\n" +
                "      \"type\": \"string\"\n" +
                "    },\n" +
                "    \"favouriteFoods\": {\n" +
                "      \"type\": \"object\",\n" +
                "      \"properties\": {\n" +
                "        \"breakfast\": {\n" +
                "          \"type\": \"string\"\n" +
                "        },\n" +
                "        \"lunch\": {\n" +
                "          \"type\": \"string\"\n" +
                "        },\n" +
                "        \"dinner\": {\n" +
                "          \"type\": \"array\",\n" +
                "          \"items\": [\n" +
                "            {\n" +
                "              \"type\": \"string\"\n" +
                "            },\n" +
                "            {\n" +
                "              \"type\": \"string\"\n" +
                "            }\n" +
                "          ]\n" +
                "        }\n" +
                "      },\n" +
                "      \"required\": [\n" +
                "        \"breakfast\",\n" +
                "        \"lunch\",\n" +
                "        \"dinner\"\n" +
                "      ]\n" +
                "    },\n" +
                "    \"firstName\": {\n" +
                "      \"type\": \"string\"\n" +
                "    },\n" +
                "    \"id\": {\n" +
                "      \"type\": \"integer\"\n" +
                "    },\n" +
                "    \"jobs\": {\n" +
                "      \"type\": \"array\",\n" +
                "      \"items\": [\n" +
                "        {\n" +
                "          \"type\": \"string\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"type\": \"string\"\n" +
                "        }\n" +
                "      ]\n" +
                "    },\n" +
                "    \"lastName\": {\n" +
                "      \"type\": \"string\"\n" +
                "    },\n" +
                "    \"marks\": {\n" +
                "      \"type\": \"array\",\n" +
                "      \"items\": [\n" +
                "        {\n" +
                "          \"type\": \"object\",\n" +
                "          \"properties\": {\n" +
                "            \"english\": {\n" +
                "              \"type\": \"integer\"\n" +
                "            },\n" +
                "            \"german\": {\n" +
                "              \"type\": \"integer\"\n" +
                "            }\n" +
                "          },\n" +
                "          \"required\": [\n" +
                "            \"english\",\n" +
                "            \"german\"\n" +
                "          ]\n" +
                "        },\n" +
                "        {\n" +
                "          \"type\": \"object\",\n" +
                "          \"properties\": {\n" +
                "            \"english\": {\n" +
                "              \"type\": \"integer\"\n" +
                "            },\n" +
                "            \"german\": {\n" +
                "              \"type\": \"integer\"\n" +
                "            }\n" +
                "          },\n" +
                "          \"required\": [\n" +
                "            \"english\",\n" +
                "            \"german\"\n" +
                "          ]\n" +
                "        }\n" +
                "      ]\n" +
                "    }\n" +
                "  },\n" +
                "  \"required\": [\n" +
                "    \"email\",\n" +
                "    \"favouriteFoods\",\n" +
                "    \"firstName\",\n" +
                "    \"id\",\n" +
                "    \"jobs\",\n" +
                "    \"lastName\",\n" +
                "    \"marks\"\n" +
                "  ]\n" +
                "}";

    }

    public static String sampleJson2() {
        return "  {\n" +
                "    \"email\": \"bart.baker@gmail.com\",\n" +
                "    \"favouriteFoods\": {\n" +
                "      \"breakfast\": \"cereal\",\n" +
                "      \"lunch\": \"fruits\",\n" +
                "      \"dinner\": [\n" +
                "        \"rice\",\n" +
                "        \"beans\"\n" +
                "      ]\n" +
                "    },\n" +
                "    \"firstName\": \"Agent\",\n" +
                "    \"id\": 47,\n" +
                "    \"jobs\": [\n" +
                "      \"player\",\n" +
                "      \"singer\"\n" +
                "    ],\n" +
                "    \"lastName\": \" \",\n" +
                "    \"marks\": [\n" +
                "      {\n" +
                "        \"english\": 24,\n" +
                "        \"german\": 30\n" +
                "      },\n" +
                "      {\n" +
                "        \"english\": 27,\n" +
                "        \"german\": 29\n" +
                "      }\n" +
                "    ]\n" +
                "  }";
    }
}
