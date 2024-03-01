package Util;

import java.util.List;
import java.util.Random;

public class Helper {

    //vraca nam vrednost elementa iz niza, kakav god niz da prosledimo!!!!
    //java generic

    public static <T> T returnRandomElement (List<T> array) {

        if (array == null || array.isEmpty()) {
            throw new IllegalArgumentException("Array is empty.");
        }

        Random random = new Random();
        return array.get(random.nextInt(array.size()));
    }
}
