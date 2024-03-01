package POJO.RoomTests.Response;

import POJO.RoomTypeTests.Response.RoomTypeResponse;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RoomResponse {

    @JsonProperty("id")
    private int id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("number")
    private String number;
    @JsonProperty("type")
    private RoomTypeResponse type;
    @JsonProperty("price")
    private double price;

    public int getId() {
        return id;
    }

    public RoomResponse setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public RoomResponse setName(String name) {
        this.name = name;
        return this;
    }

    public String getNumber() {
        return number;
    }

    public RoomResponse setNumber(String number) {
        this.number = number;
        return this;
    }

    public RoomTypeResponse getType() {
        return type;
    }

    public RoomResponse setType(RoomTypeResponse type) {
        this.type = type;
        return this;
    }

    public double getPrice() {
        return price;
    }

    public RoomResponse setPrice(double price) {
        this.price = price;
        return this;
    }
}
