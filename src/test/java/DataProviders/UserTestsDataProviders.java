package DataProviders;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class UserTestsDataProviders {

    @DataProvider(name="dpSuccessfullyLoginAsAUser")
    public Object[][] dpSuccessfullyLoginAsAUser () throws IOException {
        String jsonFilePath = "src/test/resources/userTests/successfullyLoginAsAUser.json";

        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File(jsonFilePath);
        List<Map<String, String>> dataList = objectMapper.readValue(file,
                new TypeReference<List<Map<String, String>>>() {});

        //listu treba pretvoriti u dvodimenzionalni niz
        //prvi parametar je koliko redova imamo - size - dinamicki podatak, koliko redova imamo u json fajlu
        //drugi parametar je koliko kolona imamo

        Object [][] data = new Object[dataList.size()][3]; //ovo kreira dvodimenzionalni niz koji punimo podacima koriscenjem petlje

        for (int i = 0; i < dataList.size(); i ++) {
            Map<String, String> dataMap = dataList.get(i);
            data[i][0] = dataMap.get("email");
            data[i][1] = dataMap.get("password");
            data[i][2] = dataMap.get("level");
        }

        return data;
    }
}
