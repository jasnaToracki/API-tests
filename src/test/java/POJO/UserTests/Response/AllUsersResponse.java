package POJO.UserTests.Response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AllUsersResponse {
    @JsonProperty
    private UserResponseUser[] data;

    public UserResponseUser[] getData() {
        return data;
    }

    public AllUsersResponse setData(UserResponseUser[] data) {
        this.data = data;
        return this;
    }
}
