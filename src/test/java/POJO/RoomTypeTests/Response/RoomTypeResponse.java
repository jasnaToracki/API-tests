package POJO.RoomTypeTests.Response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RoomTypeResponse {
    private int id;
    private String name;
    private int beds;
    private int spaces;
    @JsonProperty("max_people")
    private int maxPeople;


    public int getId() {
        return id;
    }

    public RoomTypeResponse setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public RoomTypeResponse setName(String name) {
        this.name = name;
        return this;
    }

    public int getBeds() {
        return beds;
    }

    public RoomTypeResponse setBeds(int beds) {
        this.beds = beds;
        return this;
    }

    public int getSpaces() {
        return spaces;
    }

    public RoomTypeResponse setSpaces(int spaces) {
        this.spaces = spaces;
        return this;
    }

    public int getMaxPeople() {
        return maxPeople;
    }

    public RoomTypeResponse setMaxPeople(int maxPeople) {
        this.maxPeople = maxPeople;
        return this;
    }
}
