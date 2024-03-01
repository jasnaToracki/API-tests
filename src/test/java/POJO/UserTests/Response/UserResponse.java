package POJO.UserTests.Response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserResponse {
    @JsonProperty("user")
    private UserResponseUser user;
    @JsonProperty("token")
    private String token;


    public UserResponseUser getUser() {
        return user;
    }
    public void setUser(UserResponseUser user) {
        this.user = user;
    }
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }

}
