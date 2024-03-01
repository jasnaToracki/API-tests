package CORE;

//klasa koja sadrzi sve putanje
public class URL {
    public static final String BASE_URL = "https://api.qa.rs/api";

    /**
     * /booking
     */
    public static final String BOOKING_CREATE = "/booking";
    public static final String BOOKING_GET_ALL = "/booking";
    public static final String BOOKING_DELETE = "/booking/%d";

    /**
     * /room
     */

    public static final String ROOM_GET_ALL = "/room";
    public static final String ROOM_GET_DETAILS = "/room/%d";

    /**
     * /room_type
     */

    /**
     * /user
     */
    public static final String USER_GET_ALL = "/users";
    public static final String USER_REGISTER = "/user/register";
    public static final String USER_LOGIN = "/user/login";
    public static final String USER_BOOKING = "/booking";
    public static final String USER_DELETE = "/user/%d";
}
