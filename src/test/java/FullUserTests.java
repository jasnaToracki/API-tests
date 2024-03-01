import CORE.BaseTest;
import CORE.URL;
import POJO.BookingTests.Request.CreateBookingRequest;
import POJO.BookingTests.Response.BookingResponse;
import POJO.ErrorResponses.UnprocessableContentResponse;
import POJO.UserTests.Request.LoginRequest;
import POJO.UserTests.Request.RegisterUserRequest;
import POJO.UserTests.Response.UserResponse;
import io.restassured.response.Response;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;


public class FullUserTests extends BaseTest {

    public FullUserTests () {
        super();               //with super() method in constructor we call parent constructor
    }
    private RegisterUserRequest userRequest;
    private UserResponse userResponse;
    private CreateBookingRequest bookingRequest;
    private BookingResponse bookingResponse;

    @BeforeClass
    public void setUp () {
    }

    @AfterClass
    public void tearDown() {
    }


    @Test
    public void testUserRegistration () {
        userRequest = RegisterUserRequest.fake();

        Response response = given()
                .body(userRequest)
                .when()
                .post(URL.USER_REGISTER);

        if (response.statusCode() == 200) {
            userResponse = response
                    .then()
                    .extract()
                    .as(UserResponse.class);

            softAssert.assertNotEquals(userResponse.getUser().getId(), 0);
        } else if (response.statusCode() == 422) {
            UnprocessableContentResponse error = response
                    .then()
                    .extract()
                    .as(UnprocessableContentResponse.class);

            String printMessage = "Error response: '%s'".formatted(        //%s - String
                    error.getMessage()
            );
            System.out.println(printMessage);
            for (Map.Entry<String, List<String>> entry : error.getErrors().entrySet()) {
                String fieldName = entry.getKey();
                List<String> fieldValues = entry.getValue();

                for (String value : fieldValues) {
                    System.out.println(fieldName + " : " + value);
                }
            }
        }

        softAssert.assertEquals(response.statusCode(), 200);
        softAssert.assertAll();
    }

    @Test
    public void testUserLogin () {
        LoginRequest loginRequest = new LoginRequest(userRequest.getEmail(), userRequest.getPassword());

        UserResponse user = given()
                .body(loginRequest)
                .when()
                .post(URL.USER_LOGIN)
                .then()
                .statusCode(200)
                .extract()
                .as(UserResponse.class);

        softAssert.assertEquals(user.getUser().getLevel(), userResponse.getUser().getLevel());
        softAssert.assertEquals(user.getUser().getId(), userResponse.getUser().getId());
        softAssert.assertAll();
    }

    @Test
    public void testUserDelete () throws IOException {
        givenWithAdmin()
                .when()
                .delete(URL.USER_DELETE.formatted(userResponse.getUser().getId()))
                .then()
                .assertThat()
                .statusCode(200);
    }

}
