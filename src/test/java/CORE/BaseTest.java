package CORE;

import Util.Authentication;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.util.Random;

public class BaseTest {
    protected SoftAssert softAssert = new SoftAssert();

    public BaseTest () {
        RestAssured.baseURI = URL.BASE_URL;
    }

    public static RequestSpecification given () {
        return RestAssured.given().contentType(ContentType.JSON);
    }

//    public static RequestSpecification givenWithAdmin () throws IOException {
//        return given()
//                .header("Authorization", "Bearer %s".formatted(Authentication.getAdminAuthToken()));
//    }

    public static RequestSpecification givenWithAdmin () throws IOException {
        return givenWithAuth(Authentication.getAdminAuthToken());
    }

    //dinamicki kod, prosledjujemo token nekog korisnika koji nije admin
    public static RequestSpecification givenWithAuth (String token) {
        return given()
                .header("Authorization", "Bearer %s".formatted(token));
    }

    public static int random (int min, int max) {
        Random rnd = new Random();
        return rnd.nextInt((max - min + 1) + min);
    }

    public static int random (int max) {    //overload method
        return random(0, max);
    }
}
