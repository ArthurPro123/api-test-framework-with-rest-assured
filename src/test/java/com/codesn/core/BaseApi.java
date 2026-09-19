package com.codesn.core;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;

public abstract class BaseApi {

    @BeforeAll
    public static void globalSetup() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    protected static RequestSpecification buildSpec(String baseUri, int timeoutMs) {
        return new RequestSpecBuilder()
                .setBaseUri(baseUri)
                .setContentType(ContentType.JSON)
                .setConfig(RestAssuredConfig.config()
                        .httpClient(HttpClientConfig.httpClientConfig()
                                .setParam("http.connection.timeout", timeoutMs)
                                .setParam("http.socket.timeout", timeoutMs)))
                .build();
    }


    protected static RequestSpecification specWithAuthBearer(RequestSpecification baseSpec, String token) {
        return new RequestSpecBuilder()
            .addRequestSpecification(baseSpec)
            .addHeader("Authorization", "Bearer " + token)
            .build();
    }

    protected static RequestSpecification specWithAuthCookie(
            RequestSpecification baseSpec, String name, String value) {
        return new RequestSpecBuilder()
                .addRequestSpecification(baseSpec)
                .addHeader("Cookie", name + "=" + value)
                .build();
    }

}
