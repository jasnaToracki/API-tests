package Util;

import CORE.BaseTest;
import CORE.URL;
import POJO.UserTests.Response.AllUsersResponse;
import POJO.UserTests.Response.UserResponseUser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserHelper extends BaseTest {

    //overload
    public static int getRandomUserId () throws IOException {
        Integer[] emptyArray = new Integer[0];
        return getRandomUserId(emptyArray);
    }
    public static int getRandomUserId (Integer[] excludeIds) throws IOException {
        List<Integer> exclusionList = Arrays.asList(excludeIds);
        AllUsersResponse users = givenWithAdmin()
                .when()
                .get(URL.USER_GET_ALL)
                .then()
                .extract()
                .as(AllUsersResponse.class);

        List<Integer> userIds = new ArrayList<>();
        for (UserResponseUser user : users.getData()) {

            if (!exclusionList.contains(user.getId())) {
                userIds.add(user.getId());
            }

        }
        return Helper.returnRandomElement(userIds);
    }
}
