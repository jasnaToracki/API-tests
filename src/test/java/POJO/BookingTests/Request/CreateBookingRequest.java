package POJO.BookingTests.Request;

import CORE.BaseTest;
import Util.BookingHelper;
import Util.RoomHelper;
import Util.UserHelper;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class CreateBookingRequest extends BaseTest{
    @JsonProperty("user_id")
    private int userId;
    @JsonProperty("room_id")
    private int roomId;
    @JsonProperty("start_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private String startDate;
    @JsonProperty("end_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private String endDate;
    @JsonProperty("people")
    private int people;
    @JsonProperty("with_breakfast")
    private Boolean withBreakfast;
    @JsonProperty("with_lunch")
    private Boolean withLunch;
    @JsonProperty("with_dinner")
    private Boolean withDinner;
    @JsonProperty("with_minibar")
    private Boolean withMinibar;
    @JsonProperty("with_laundry")
    private Boolean withLaundry;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public int setRandomRoomId() {
        Random random = new Random();
        this.roomId = random.nextInt(50) + 1;
        return this.roomId;
    }

    public LocalDate getStartDate() {
        return LocalDate.parse(startDate);
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return LocalDate.parse(endDate);
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public int getPeople() {
        return people;
    }

    public void setPeople(int people) {
        this.people = people;
    }

    public boolean isWithBreakfast() {
        return withBreakfast;
    }

    public void setWithBreakfast(boolean withBreakfast) {
        this.withBreakfast = withBreakfast;
    }

    public boolean isWithLunch() {
        return withLunch;
    }

    public void setWithLunch(boolean withLunch) {
        this.withLunch = withLunch;
    }

    public boolean isWithDinner() {
        return withDinner;
    }

    public void setWithDinner(boolean withDinner) {
        this.withDinner = withDinner;
    }

    public boolean isWithMinibar() {
        return withMinibar;
    }

    public void setWithMinibar(boolean withMinibar) {
        this.withMinibar = withMinibar;
    }

    public boolean isWithLaundry() {
        return withLaundry;
    }

    public void setWithLaundry(boolean withLaundry) {
        this.withLaundry = withLaundry;
    }

    public static CreateBookingRequest fake () throws Exception {
        return fake(90, 180, 1, 10);
    }
    public static CreateBookingRequest fake (
            int minDaysInFuture,
            int maxDaysInFuture,
            int minDaysOccupied,
            int maxDaysOccupied)
            throws Exception {
        CreateBookingRequest booking = new CreateBookingRequest();

        Integer[] excludeIds = {1, 100};
        int userId = UserHelper.getRandomUserId(excludeIds);
        int roomId = RoomHelper.getRandomRoomId();

        booking.setUserId(userId);
        booking.setRoomId(roomId);
        booking.setPeople(1);
        booking.setWithBreakfast(Math.random() < 0.5);
        booking.setWithLunch(Math.random() < 0.5);
        booking.setWithDinner(Math.random() < 0.5);
        booking.setWithMinibar(Math.random() < 0.5);
        booking.setWithLaundry(Math.random() < 0.5);

        Faker faker = new Faker();
        int timeoutNumber = 0;

        do {
            LocalDate date1 = faker.date().future(maxDaysInFuture, minDaysInFuture, TimeUnit.DAYS)
                    .toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate date2 = date1.plusDays(BaseTest.random(minDaysOccupied, maxDaysOccupied));

            booking.setStartDate(date1.format(DateTimeFormatter.ISO_LOCAL_DATE));
            booking.setEndDate(date2.format(DateTimeFormatter.ISO_LOCAL_DATE));

            timeoutNumber++;

            if (timeoutNumber > 60 ) {
                throw new Exception ("Date check operation timed out in CreateBookingRequest.fake()");
            }

        } while (!BookingHelper.isRoomFree(booking));

        return booking;
    }
}
