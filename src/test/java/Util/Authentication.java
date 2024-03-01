package Util;

import DataProviders.UserTestsDataProviders;
import POJO.UserTests.Request.LoginRequest;
import POJO.UserTests.Response.UserResponse;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import java.io.IOException;

public class Authentication {
    private static String adminToken = null;
    private static String receptionistToken = null;

    public static String getAdminAuthToken() throws IOException {
        if (adminToken == null) {
            String email = PropertyHelper.getAdminEmail();
            String password = PropertyHelper.getAdminPassword();
            adminToken = getAuthenticationToken(email, password);
        }
        return adminToken;
    }

//        UserTestsDataProviders dp = new UserTestsDataProviders();
//        Object [][] data = dp.dpSuccessfullyLoginAsAUser();
//
//        String email = (String) data[0][0];
//        String password = (String) data[0][1];

    public static String getReceptionistAuthToken () throws IOException {

        if (receptionistToken == null) {
            String email = PropertyHelper.getReceptionistEmail();
            String password = PropertyHelper.getReceptionistPassword();
            receptionistToken = getAuthenticationToken(email, password);
        }
        return receptionistToken;
    }

//        UserTestsDataProviders dp = new UserTestsDataProviders();
//        Object [][] data = dp.dpSuccessfullyLoginAsAUser();
//
//        String email = (String) data[1][0];
//        String password = (String) data[1][1];
//
//        return getAuthenticationToken(email, password);


    public static String getAuthenticationToken (String email, String password) {
        LoginRequest loginRequest = new LoginRequest(email, password);

        UserResponse user = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(loginRequest)
                .post("https://api.qa.rs/api/user/login")
                .then()
                .statusCode(200)
                .extract()
                .as(UserResponse.class);

        return user.getToken();
    }
}
