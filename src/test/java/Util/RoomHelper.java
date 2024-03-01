package Util;

import CORE.BaseTest;
import CORE.URL;
import POJO.RoomTests.Response.RoomResponse;
import POJO.RoomTests.Response.RoomsResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RoomHelper extends BaseTest {
    public static int getRandomRoomId () throws IOException {
        RoomsResponse rooms = givenWithAdmin()
                .when()
                .get(URL.ROOM_GET_ALL)
                .then()
                .statusCode(200)
                .extract()
                .as(RoomsResponse.class);

        List<Integer> roomIds = new ArrayList<>();
        for (RoomResponse room : rooms.getData()) {
            roomIds.add(room.getId());
        }
        return Helper.returnRandomElement(roomIds);
    }
}
