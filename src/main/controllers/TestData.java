package main.controllers;

import java.util.HashMap;


public class TestData {
    public static final String URL = "http://0.0.0.0:8182";
    public static String generalEndPoint = "/api/v1/contacts";

    // header info
    public static String contentTypeKey = "Content-Type";
    public static String contentType = "application/json";

    // status code and message
    public static HashMap<String, String> statusCodeMessages = new HashMap<String, String>(){
        {
            put("200", "OK");
            put("201", "Created");
            put("404", "Not Found");
        }
    };

    // json data for adding and updating user info
    public static HashMap<String, String> userJsonData = new HashMap<String, String>(){
        {
            put("jsonContent", "{\"firstName\":\"userName\",\"lastName\":\"userLastName\", \"email\": \"user@gmail.com\"}");
            put("jsonContentUpdated", "{\"email\":\"newuser@gmail.com\",\"firstName\":\"newName\",\"lastName\":\"newLastName\"}");
            put("jsonContentPartialUpdated", "{\"firstName\":\"newName\"}");
        }
    };

    // data from json to check
    public static HashMap<String, String> userData = new HashMap<String, String>(){
        {
            put("userName", "userName");
            put("userLastName", "userLastName");
            put("userEmail", "user@gmail.com");
            put("updatedName", "newName");
            put("updatedLastName", "newLastName");
            put("updatedEmail", "newuser@gmail.com");
        }
    };

    // query parameters
    public static HashMap<String, String> queryParams = new HashMap<String, String>(){
        {
            put("firstName", "firstName=");
            put("email", "email=");
        }
    };
}
