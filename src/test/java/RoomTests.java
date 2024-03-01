import CORE.BaseTest;
import CORE.URL;
import POJO.RoomTests.Response.RoomDetailsResponse;
import POJO.RoomTests.Response.RoomResponse;
import POJO.RoomTests.Response.RoomsResponse;
import Util.Authentication;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class RoomTests extends BaseTest {
    private RoomResponse selectedRoom = null;

    public RoomTests() {
        super();
    }

    @Test
    public void testListAllRooms() throws IOException {
        RoomsResponse rooms = givenWithAdmin()
                .when()
                .get(URL.ROOM_GET_ALL)
                .then()
                .statusCode(200)
                .extract()
                .as(RoomsResponse.class);

        int numberOfRooms = rooms.getData().length;

        softAssert.assertTrue(numberOfRooms > 0);

        selectedRoom = rooms.getData()[random(numberOfRooms)];
        softAssert.assertAll();
    }

    @Test
    public void testGetRoomDetails() throws IOException {
        RoomDetailsResponse room = givenWithAdmin()
                .when()
                .get(URL.ROOM_GET_DETAILS.formatted(selectedRoom.getId()))
                .then()
                .statusCode(200)
                .extract()
                .as(RoomDetailsResponse.class);


        softAssert.assertEquals(room.getData().getId(), selectedRoom.getId());
        softAssert.assertEquals(room.getData().getNumber(), selectedRoom.getNumber());
        softAssert.assertAll();
    }































    @Ignore // OLD
    @Test
    public void listAllRoomTypes() throws IOException {
        Response response = given()
                .header("Authorization", "Bearer " + Authentication.getAdminAuthToken())
                .when()
                .get("/room_type");

        List<Integer> roomIds = response.getBody().jsonPath().getList("data.id");
        System.out.println("RoomID: " + roomIds);

        Assert.assertFalse(roomIds.isEmpty());

        response.then().statusCode(200);
    }
    @Ignore  // OLD
    @Test
    public void getRoomType () throws IOException {
        Response response = given()
                .header("Authorization", "Bearer " + Authentication.getAdminAuthToken())
                .when()
                .get("/room_type/1");

        int roomTypeId = response.getBody().jsonPath().getInt("data.id");

        String roomType = response.getBody().jsonPath().getString("data.name");
        System.out.println("Room type: " + roomType);

        Assert.assertEquals(roomTypeId, 1);

        response.then().statusCode(200);
    }

    @Ignore  // OLD
    @Test
     public void getAllRooms () throws IOException {
        Response response = given()
                .header("Authorization", "Bearer " + Authentication.getAdminAuthToken())
                .when()
                .get("/room");

        List<String> rooms = response.getBody().jsonPath().getList("data.type.name");
        System.out.println("All rooms: " + rooms);

        List<Integer> allRoomsId = response.getBody().jsonPath().getList("data.id");
        System.out.println("All rooms id: " + allRoomsId);

        response.then().statusCode(200);
    }
    @Ignore   // OLD
    @Test
    public void getRoomId () throws IOException {
        Response response = given()
                .header("Authorization", "Bearer " + Authentication.getAdminAuthToken())
                .when()
                .get("/room/1");

        int roomTypeId = response.getBody().jsonPath().getInt("data.type.id");
        System.out.println("Room id: " + roomTypeId);
        Assert.assertEquals(roomTypeId, 1);


        response.then().statusCode(200);
    }
}
