package POJO.RoomTests.Response;


import com.fasterxml.jackson.annotation.JsonProperty;

public class RoomsResponse {
    @JsonProperty ("data")
    private RoomResponse[] data;

    public RoomResponse[] getData() {
        return data;
    }

    public RoomsResponse setData(RoomResponse[] data) {
        this.data = data;
        return this;
    }
}
