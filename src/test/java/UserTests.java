import DataProviders.UserTestsDataProviders;
import POJO.UserTests.Request.LoginRequest;
import POJO.UserTests.Request.RegisterUserRequest;
import POJO.UserTests.Response.UserResponse;
import Util.Authentication;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

import static io.restassured.RestAssured.*;

public class UserTests {
    public UserTests() {
        RestAssured.baseURI = "https://api.qa.rs/api";
    }

//    @BeforeClass
//    public void setUp () throws IOException {
//        bearerToken = Authentication.getAdminAuthToken();
//    }

    @BeforeClass
    public void setUp () {}

    @AfterClass
    public void tearDown () {}


    @Test (dataProviderClass = UserTestsDataProviders.class, dataProvider = "dpSuccessfullyLoginAsAUser")
    public void successfullyLoginAsAUser(String email, String password, String level) {
        LoginRequest loginRequest = new LoginRequest(email, password);


        UserResponse user = given()
                .contentType(ContentType.JSON)
                .body(loginRequest)
                .when()
                .post("/user/login")
                .then()
                .statusCode(200)
                .extract()
                .as(UserResponse.class);

//        if (bearerToken2 == null) {
//            this.bearerToken2 = user.getToken();
//        }
        Assert.assertEquals(user.getUser().getLevel(), Integer.parseInt(level));  //ovo proverava da li ulogovani korisnik ima level pristupa koji treba da ima
    }

    @Test
    public void successfullyRegisterNewUser () {
        RegisterUserRequest request = new RegisterUserRequest();
        request.setName("Customer")
                .setEmail("customer@hotel.local")
                .setPassword("StrongPa$$word!")
                .setPasswordConfirmation("StrongPa$$word!");

        UserResponse response = given()
                .contentType(ContentType.JSON)
                .body(request)
                .when()
                .post("/user/register")
                .then()
                .statusCode(200)
                .extract()
                .as(UserResponse.class);

        Assert.assertNotEquals(response.getUser().getId(), 0);
    }

    @Test(dependsOnMethods = "successfullyLoginAsAUser")
    public void listAllUsers() throws IOException {
        given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + Authentication.getAdminAuthToken())
                .when()
                .get("/users")
                .then()
                .statusCode(200);
    }

    @Test
    public void deleteUser () throws IOException {
        given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + Authentication.getAdminAuthToken())
                .when()
                .delete("/user/109")
                .then()
                .assertThat()
                .statusCode(200);

    }

}
