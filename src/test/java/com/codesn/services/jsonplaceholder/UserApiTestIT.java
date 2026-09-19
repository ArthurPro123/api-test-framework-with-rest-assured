package com.codesn.services.jsonplaceholder;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Tag;

import static io.restassured.RestAssured.*;  // Includes given, when, then...

import static org.hamcrest.Matchers.*;  // Enables fluent assertions 
                                        // (equalTo(), hasItem(), containsString()...).
                                    	// that can be used with body() 
                                        // and other REST Assured validation methods

import com.codesn.services.jsonplaceholder.datatemplates.UserPayload;

import com.codesn.core.utils.TestLogger;



public class UserApiTestIT extends JsonPlaceholderBaseApi {

    @Test
    public void loggerPrintsPayload() {
        TestLogger.log("Smoke Test", "{\"hello\":\"world\",\"n\":42}");
    }

    @Test
    @Tag("SLO")
    public void testGetUserById() {
        // GET request to JSONPlaceholder (free public API)
        given()
            .spec(REQUEST_SPEC)
        .when()
            .get("/users/1")
        .then()
            .statusCode(200)
            .body("id", equalTo(1))
            .body("name", equalTo("Leanne Graham"))
            .body("email", containsString("@"))
            .log().all();
    }

    @Test
    public void testCreateUser() {

        UserPayload requestBody = new UserPayload("John Doe", "john@example.com", "johndoe");

        given()
            .spec(REQUEST_SPEC)
            .body(requestBody)
        .when()
            .post("/users")
        .then()
            .statusCode(201)
            .body("name", equalTo("John Doe"))
            .body("id", notNullValue());
    }

    @Test
    public void testUpdateUser() {

        UserPayload updateBody = new UserPayload("Updated Name", "updated@example.com", "johndoe");

        given()
            .spec(REQUEST_SPEC)
            .body(updateBody)
        .when()
            .put("/users/1")
        .then()
            .statusCode(200)
            .body("name", equalTo("Updated Name"));
    }

    @Test
    public void testDeleteUser() {
        given()
            .spec(REQUEST_SPEC)
        .when()
            .delete("/users/1")
        .then()
            .statusCode(200);
    }

    @Test
    public void testGetUserWithQueryParameter() {
        given()
            .spec(REQUEST_SPEC)
            .queryParam("userId", 1)
        .when()
            .get("/posts")
        .then()
            .statusCode(200)
            .body("size()", greaterThan(0))
            .body("[0].userId", equalTo(1));
    }

    // When a test needs different headers:
    @Test
    public void testWithCustomHeader() {
        given()
            .spec(REQUEST_SPEC)
            .header("X-Custom-Header", "value")
        .when()
            .get("/users/1")
        .then()
            .statusCode(200);
    }

}

