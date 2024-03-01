package POJO.BookingTests.Response;

import POJO.BookingTests.Request.CreateBookingRequest;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public class BookingResponseBooking {
    @JsonProperty("id")
    private int id;
    @JsonProperty("user")
    private int user;
    @JsonProperty("room")
    private int room;
    @JsonProperty("start_date")
    private LocalDate startDate;
    @JsonProperty("end_date")
    private LocalDate endDate;
    @JsonProperty("amenities")
    private BookingResponseAmenities amenities;
    @JsonProperty("persons")
    private int persons;
    @JsonProperty("total_price")
    private float totalPrice;

    public int getId() {
        return id;
    }

    public BookingResponseBooking setId(int id) {
        this.id = id;
        return this;
    }

    public int getUser() {
        return user;
    }

    public BookingResponseBooking setUser(int user) {
        this.user = user;
        return this;
    }

    public int getRoom() {
        return room;
    }

    public BookingResponseBooking setRoom(int room) {
        this.room = room;
        return this;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public BookingResponseBooking setStartDate(LocalDate startDate) {
        this.startDate = startDate;
        return this;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public BookingResponseBooking setEndDate(LocalDate endDate) {
        this.endDate = endDate;
        return this;
    }

    public BookingResponseAmenities getAmenities() {
        return amenities;
    }

    public BookingResponseBooking setAmenities(BookingResponseAmenities amenities) {
        this.amenities = amenities;
        return this;
    }

    public int getPersons() {
        return persons;
    }

    public BookingResponseBooking setPersons(int persons) {
        this.persons = persons;
        return this;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public BookingResponseBooking setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
        return this;
    }

    public boolean overlapsWith (CreateBookingRequest otherBooking) {
        return !(endDate.isBefore(otherBooking.getStartDate()) ||
                startDate.isAfter(otherBooking.getEndDate()));
    }
}