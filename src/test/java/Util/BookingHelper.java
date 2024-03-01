package Util;

import CORE.BaseTest;
import CORE.URL;
import POJO.BookingTests.Request.CreateBookingRequest;
import POJO.BookingTests.Response.BookingResponseBooking;
import POJO.BookingTests.Response.GetBookingsResponse;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;

public class BookingHelper extends BaseTest {

    private static GetBookingsResponse cachedBookings = null;
    private static Instant cachedAt = null;

    public static boolean isRoomFree(CreateBookingRequest bookingRequest) throws IOException {
        boolean expired = false;

        if (cachedAt != null) {
            Duration duration = Duration.between(cachedAt, Instant.now());
            expired = duration.toMinutes() > 1;           //ako je proslo vise od minuta onda je expired
        }

        if (cachedBookings == null || cachedAt == null || expired) {
            cachedBookings = givenWithAdmin()
                    .when()
                    .get(URL.BOOKING_GET_ALL)
                    .then()
                    .extract()
                    .as(GetBookingsResponse.class);

            cachedAt = Instant.now();
        }

        for (BookingResponseBooking booking : cachedBookings.getData()) {
            if (bookingRequest.getRoomId() == booking.getRoom() &&
                    booking.overlapsWith(bookingRequest)) {
                return false;
            }
        }
        return true;
    }
}
