package POJO.BookingTests.Response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BookingResponse {
    @JsonProperty("booking")
    private BookingResponseBooking booking;

    @JsonProperty("message")
    private String message;

    public BookingResponseBooking getBooking() {
        return booking;
    }

    public BookingResponse setBooking(BookingResponseBooking booking) {
        this.booking = booking;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public BookingResponse setMessage(String message) {
        this.message = message;
        return this;
    }
}