package POJO.BookingTests.Response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BookingResponseAmenities {
    @JsonProperty("breakfast")
    private boolean breakfast;
    @JsonProperty("lunch")
    private boolean lunch;
    @JsonProperty("dinner")
    private boolean dinner;
    @JsonProperty("minibar")
    private boolean minibar;
    @JsonProperty("laundry")
    private boolean laundry;

    public boolean isBreakfast() {
        return breakfast;
    }

    public BookingResponseAmenities setBreakfast(boolean breakfast) {
        this.breakfast = breakfast;
        return this;
    }

    public boolean isLunch() {
        return lunch;
    }

    public BookingResponseAmenities setLunch(boolean lunch) {
        this.lunch = lunch;
        return this;
    }

    public boolean isDinner() {
        return dinner;
    }

    public BookingResponseAmenities setDinner(boolean dinner) {
        this.dinner = dinner;
        return this;
    }

    public boolean isMinibar() {
        return minibar;
    }

    public BookingResponseAmenities setMinibar(boolean minibar) {
        this.minibar = minibar;
        return this;
    }

    public boolean isLaundry() {
        return laundry;
    }

    public BookingResponseAmenities setLaundry(boolean laundry) {
        this.laundry = laundry;
        return this;
    }
}