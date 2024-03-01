import CORE.BaseTest;
import CORE.URL;
import POJO.BookingTests.Request.CreateBookingRequest;
import POJO.BookingTests.Response.BookingResponse;
import POJO.BookingTests.Response.BookingResponseBooking;
import POJO.BookingTests.Response.GetBookingsResponse;
import POJO.ErrorResponses.UnprocessableContentResponse;
import Util.Authentication;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

 public class BookingTests extends BaseTest {
     BookingResponse createdBooking = null;
     GetBookingsResponse allBookings = null;
    public BookingTests() {
        super();
    }


     @Test
     public void a() throws Exception {
         CreateBookingRequest request = CreateBookingRequest.fake();

         ObjectMapper objectMapper = new ObjectMapper();
         objectMapper.findAndRegisterModules();

         System.out.println(objectMapper.writeValueAsString(request));
     }

     @Test
     public void createABookingTest() throws Exception {

         CreateBookingRequest request = CreateBookingRequest.fake(25, 80, 5, 25);

         Response response = givenWithAdmin()
                 .body(request)
                 .when()
                 .post(URL.BOOKING_CREATE);


         if (response.statusCode() == 200) {
             createdBooking = response
                 .then()
                 .statusCode(200)
                 .extract()
                 .as(BookingResponse.class);

             softAssert.assertEquals(createdBooking.getMessage(), "Success");
             softAssert.assertEquals(createdBooking.getBooking().getUser(), request.getUserId());
             softAssert.assertEquals(createdBooking.getBooking().getRoom(), request.getRoomId());
             softAssert.assertEquals(createdBooking.getBooking().getStartDate(), request.getStartDate());
             softAssert.assertEquals(createdBooking.getBooking().getEndDate(), request.getEndDate());
             softAssert.assertEquals(createdBooking.getBooking().getPersons(), request.getPeople());
             softAssert.assertTrue(createdBooking.getBooking().getTotalPrice() > 0);
             softAssert.assertEquals(createdBooking.getBooking().getAmenities().isBreakfast(), request.isWithBreakfast());
             softAssert.assertEquals(createdBooking.getBooking().getAmenities().isLunch(), request.isWithLunch());
             softAssert.assertEquals(createdBooking.getBooking().getAmenities().isDinner(), request.isWithDinner());
             softAssert.assertEquals(createdBooking.getBooking().getAmenities().isMinibar(), request.isWithMinibar());
             softAssert.assertEquals(createdBooking.getBooking().getAmenities().isLaundry(), request.isWithLaundry());

         } else if (response.statusCode() == 422) {
             UnprocessableContentResponse error = response
                     .then()
                     .extract()
                     .as(UnprocessableContentResponse.class);

             for (Map.Entry<String, List<String>> entry : error.getError().entrySet()) {
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

     @Ignore
     @Test
     public void getAllBookings() throws IOException {  // me
         Response response = given()
                 .contentType(ContentType.JSON)
                 .header("Authorization", "Bearer " + Authentication.getAdminAuthToken())
                 .when()
                 .get(URL.BOOKING_GET_ALL)
                 .then()
                 .statusCode(200)
                 .extract()
                 .response();

         System.out.println(response.getBody().asString());
     }

     @Test
     public void geAllBookingsTest () throws IOException {
        File schemaFile = new File ("src/test/resources/BookingTests/schemas/getAllBookingsTest.json");

        allBookings = givenWithAdmin()
                .when()
                .get(URL.BOOKING_GET_ALL)
                .then()
                .statusCode(200)
                .assertThat()
                .body(JsonSchemaValidator.matchesJsonSchema(schemaFile))
                .extract()
                .as(GetBookingsResponse.class);

        softAssert.assertTrue(allBookings.getData().length > 0);
        softAssert.assertAll();
     }

     @Test
     public void wasBookingCreatedTest () {
        boolean bookingWasFound = false;

        for (BookingResponseBooking booking : allBookings.getData()) {
            if (booking.getId() == createdBooking.getBooking().getId()) {
                bookingWasFound = true;
                break;
            }
        }

        softAssert.assertTrue(bookingWasFound);
        softAssert.assertAll();
     }

     @Test
     public void getBookingDetails() throws IOException {
         Response response = givenWithAdmin()
                 .when()
                 .get("/booking/100")
                 .then()
                 .statusCode(200)
                 .extract()
                 .response();

         System.out.println(response.asString());
     }

     @Test
     public void testDeleteBooking () throws IOException {
         givenWithAdmin()
                 .when()
                 .delete(URL.BOOKING_DELETE.formatted(createdBooking.getBooking().getId()))
                 .then()
                 .assertThat()
                 .statusCode(200);
     }

}
