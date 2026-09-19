package com.codesn.services.booking.datatemplates;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public class BookingDatesPayload {

  @JsonProperty private LocalDate checkin;
  @JsonProperty private LocalDate checkout;

    // default constructor required by Jackson
    public BookingDatesPayload() {}
  
    public BookingDatesPayload(LocalDate checkin, LocalDate checkout) {
      this.checkin = checkin;
      this.checkout = checkout;
    }

    public LocalDate getCheckin() { // Getter for Jackson use
      return checkin;
    }

    public LocalDate getCheckout() {
      return checkout;
    }

}
