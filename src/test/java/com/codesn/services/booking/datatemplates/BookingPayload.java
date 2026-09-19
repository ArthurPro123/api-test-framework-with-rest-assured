package com.codesn.services.booking.datatemplates;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;

/*
 *  01 WS-BOOKING.
 *     05 WS-ROOMID          PIC 9(5).
 *     05 WS-FIRSTNAME       PIC X(50).
 *     05 WS-LASTNAME        PIC X(50).
 *     05 WS-DEPOSITPAID     PIC X(1). (or 9(1))
 *     05 WS-BOOKING-DATES.
 *        10 WS-CHECKIN      PIC 9(8) (or date format).
 *        10 WS-CHECKOUT     PIC 9(8).
 *     05 WS-ADDITIONALNEEDS PIC X(100).
 *
 *     Both are ways of defining a hierarchical record that groups related fields together.
 */


// @JsonIgnoreType
public class BookingPayload {

  @JsonProperty private int roomid;
  @JsonProperty private String firstname;
  @JsonProperty private String lastname;
  @JsonProperty private String email;
  @JsonProperty private String phone;
  @JsonProperty private int totalprice;
  @JsonProperty private boolean depositpaid;
  @JsonProperty private BookingDatesPayload bookingdates;
  @JsonProperty private String additionalneeds;


  // Constructor 1 - default constructor required by Jackson
  public BookingPayload() {}

  // Constructor 2 - with roomid
  public BookingPayload(int roomid, String firstname, String lastname, 
    String email, String phone, int totalprice,
    boolean depositpaid, BookingDatesPayload bookingdates, String additionalneeds) {

    this.roomid = roomid;
    this.firstname = firstname;
    this.lastname = lastname;
    this.email = email;
    this.phone = phone;
    this.totalprice = totalprice;
    this.depositpaid = depositpaid;
    this.bookingdates = bookingdates;
    this.additionalneeds = additionalneeds;
  }

  // Constructor 3 - without roomid
  public BookingPayload(String firstname, String lastname, 
    String email, String phone, int totalprice, 
    boolean depositpaid, BookingDatesPayload bookingdates, String additionalneeds) {

    this.firstname = firstname;
    this.lastname = lastname;
    this.email = email;
    this.phone = phone;
    this.totalprice = totalprice;
    this.depositpaid = depositpaid;
    this.bookingdates = bookingdates;
    this.additionalneeds = additionalneeds;
  }


  /* Can work without the following, as BookingApiIT.java gets the room id from BookingResponse.java */

  public int getRoomid() {
  return roomid;
  }
  public String getFirstname() {
  return firstname;
  }
  public String getLastname() {
  return lastname;
  }
  public String getEmail() {
  return email;
  }
  public String getPhone() {
  return phone;
  }
  public int getTotalprice() {
  return totalprice;
  }
  public boolean isDepositpaid() {
  return depositpaid;
  }
  public BookingDatesPayload getBookingdates() {
  return bookingdates;
  }
  public String getAdditionalneeds() {
  return additionalneeds;
  }

}

