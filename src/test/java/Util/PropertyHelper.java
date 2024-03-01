package Util;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyHelper {
    private static final String configFile = "src/test/resources/auth/auth.properties";
    private static Properties properties = new Properties();

    public static void loadProperties () throws IOException {      //objekat koji cuva to sto smo iscitali iz fajla
        if (properties.isEmpty()) {
        try (FileInputStream propertyInput = new FileInputStream(configFile)) {
            properties.clear();
            properties.load(propertyInput);                              //ovo ce ucitati propertije iz property fajla
        }
        }
    }
    public static String getAdminEmail () throws IOException {
        loadProperties();
        return properties.getProperty("admin.email");
    }

    public static String getAdminPassword () throws IOException {
        loadProperties();
        return properties.getProperty("admin.password");
    }

    public static String getReceptionistEmail () throws IOException {
        loadProperties();
        return properties.getProperty("reception.email");
    }

    public static String getReceptionistPassword () throws IOException {
        loadProperties();
        return properties.getProperty("reception.password");
    }
}
