package com.codesn.services.booking.datatemplates;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BookingResponse {

  /*
   * Example Response:
   * {
   *   "bookingid": 1617,
   *   "booking": {
   *     "firstname": "Mark",
   *     "lastname": "Twain",
   *     "bookingdates": {
   *       "checkin": "2021-02-01",
   *       "checkout": "2021-02-03"
   *     },
   *     ...
   *   }
   * }
   */


    @JsonProperty private int bookingid;
    @JsonProperty private BookingPayload booking;  // Reuse your existing Booking class

    // Getters
    public int getBookingId() {
        return bookingid;
    }

    public BookingPayload getBooking() {
        return booking;
    }

}
