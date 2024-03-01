package POJO.BookingTests.Response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GetBookingsResponse {
    @JsonProperty("data")
    private BookingResponseBooking[] data;

    public BookingResponseBooking[] getData() {
        return data;
    }

    public GetBookingsResponse setData(BookingResponseBooking[] data) {
        this.data = data;
        return this;
    }
}
