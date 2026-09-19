package com.codesn.services.booking;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Tag;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import com.codesn.services.booking.datatemplates.BookingPayload;
import com.codesn.services.booking.datatemplates.BookingDatesPayload;
import com.codesn.services.booking.datatemplates.BookingResponse;
import com.codesn.services.booking.datatemplates.AuthPayload;

import java.time.LocalDate;

import com.codesn.core.utils.TestLogger;


public class BookingApiIT extends BookingBaseApi {

  @Test
  @Tag("smoke")
  @Tag("regression")
  @Tag("SLO")
  public void getBookingIdsReturns200() {

    given()
        .spec(REQUEST_SPEC)
    .when()
        .get()
    .then()
        .statusCode(200);
  }


  @Test
  @Tag("smoke")
  @Tag("regression")
  public void postBooking() {

    var dates = new BookingDatesPayload(
      LocalDate.of( 2021 , 1 , 1 ),
      LocalDate.of( 2021 , 1 , 3 )
    );

    var bookingPayload = new BookingPayload(
        "Mark",
        "Twain",
        "user@mail.com",
        "0544-04-05-05",
        120,
        true,
        dates,
        "Breakfast"
    );

    TestLogger.log("Create Booking", bookingPayload);


    given()
        .spec(REQUEST_SPEC)
        .body(bookingPayload)
        .log().all()
    .when()
        .post("/booking")
    .then()
        .statusCode(200)
        .body("bookingid", notNullValue())
        .log().all();
  }


  @Test
  @Tag("smoke")
  @Tag("regression")
  public void deleteBooking() {
    
    var dates = new BookingDatesPayload(
      LocalDate.of( 2021 , 2 , 1 ),
      LocalDate.of( 2021 , 2 , 3)
    );

    var bookingPayload = new BookingPayload(
        "Mark",
        "Twain",
        "user@mail.com",
        "0544-04-05-05",
        100,
        true,
        dates,
        "Breakfast"
    );


    int createdBookingId = given()
        .spec(REQUEST_SPEC)
        .body(bookingPayload)
    .when()
        .post("/booking")
    .then()
        .statusCode(200)
        .body("bookingid", notNullValue())
        .extract()
        .path("bookingid");

    
    given()
        .spec(specWithAuth())
    .when()
        .delete("/booking/" + createdBookingId)
    .then()
        .statusCode(201)
        .log().all();

  }

}
