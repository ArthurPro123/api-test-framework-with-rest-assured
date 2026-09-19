package com.codesn.services.booking;

import com.codesn.core.BaseApi;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;

import com.codesn.services.booking.datatemplates.AuthPayload;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

import com.codesn.core.utils.TestLogger;
import com.codesn.core.utils.WaitForService;

public abstract class BookingBaseApi extends BaseApi {

    protected static RequestSpecification REQUEST_SPEC;
    private static String cachedAuthToken;

    @BeforeAll
    public static void setupBooking() {

        WaitForService.waitUntilAvailable(BookingConfig.getApiBaseUrl(), 20);

        REQUEST_SPEC = buildSpec(
                BookingConfig.getApiBaseUrl(),
                BookingConfig.getTimeout());
    }


    protected RequestSpecification specWithAuth() {
        return specWithAuthCookie(REQUEST_SPEC, "token", getToken());
    }

    private static String getToken() {
        if (cachedAuthToken == null) {

            AuthPayload credentialsPayload = new AuthPayload(
                    BookingConfig.getAuthUsername(),
                    BookingConfig.getAuthPassword()
            );

            Response authResponse = given()
                    .spec(REQUEST_SPEC)
                    .body(credentialsPayload)
                    .when()
                    .post(BookingConfig.getAuthPath());

            TestLogger.log("Booking Auth", authResponse);

            cachedAuthToken = authResponse.then().extract().path("token");
        }
        return cachedAuthToken;
    } 
}
