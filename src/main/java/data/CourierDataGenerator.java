package data;

import io.qameta.allure.Step;
import org.apache.commons.lang3.RandomStringUtils;
import java.util.HashMap;
import java.util.Map;


public class CourierDataGenerator {

    @Step("Generating courier data")
    public static Map <String, String> generateCourierData() {

        HashMap <String, String> CourierData = new HashMap <>();

        CourierData.put("login", RandomStringUtils.randomAlphabetic(10));
        CourierData.put("password", RandomStringUtils.randomAlphabetic(10));
        CourierData.put("firstName", RandomStringUtils.randomAlphabetic(10));

        return CourierData;
    }
}
