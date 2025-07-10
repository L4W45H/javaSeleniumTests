package org.example;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ApiTest {

    private static final String BASE_URL = "https://dummyjson.com";

    @Test
    public void getAllProducts() {
        Response response = given()
                .when()
                .get(BASE_URL + "/products");
        assertEquals(200, response.getStatusCode());
        System.out.println(response.prettyPrint());
    }

    @Test
    public void addProduct() {
        String body = """
            { "title": "new product" }
        """;
        Response response = given()
                .header("Content-Type", "application/json")
                .body(body)
                .when()
                .post(BASE_URL + "/products/add");
        assertEquals(201, response.getStatusCode());
        System.out.println(response.prettyPrint());
    }

    @Test
    public void updateProduct() {
        String body = """
            { "title": "iPhone Galaxy +1" }
        """;
        Response response = given()
                .header("Content-Type", "application/json")
                .body(body)
                .when()
                .put(BASE_URL + "/products/1");
        assertEquals(200, response.getStatusCode());
        System.out.println(response.prettyPrint());
    }

    @Test
    public void deleteProduct() {
        Response response = given()
                .when()
                .delete(BASE_URL + "/products/1");
        assertEquals(200, response.getStatusCode());
        System.out.println(response.prettyPrint());
    }
} 