import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class MyFirstTest {

    @Test
    public void myFirstTest () {

        int expected = 2;
        int actual = 2;

        Assert.assertEquals(actual, expected);
    }

    @Test
    public void mySecondTest () {
        String expected = "TestNG";
        String actual = "TestNG";

        Assert.assertEquals(actual, expected);
    }

    //SOFT ASSERT evaluira sve asertacije
    @Test
    public void testSoftAssert () {
        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(1, 0, "1 = 0");
        softAssert.assertEquals(true, false, "true = false");
        softAssert.assertEquals(10, 10, "10 = 10");
        softAssert.assertEquals("Success", "Error", "Success = Error");

        softAssert.assertAll();
    }

}
