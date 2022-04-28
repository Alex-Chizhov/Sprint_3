package data;

import java.util.Random;
import io.qameta.allure.Step;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;


public class OrderDataGenerator {

    @Step("Generating phone number")
    private static String generatePhone(){
        int num1, num2, num3;
        int set2, set3;
        Random generator = new Random();
        num1 = generator.nextInt(7) + 1;
        num2 = generator.nextInt(8);
        num3 = generator.nextInt(8);
        set2 = generator.nextInt(643) + 100;
        set3 = generator.nextInt(8999) + 1000;

        return "+7" + " " + num1 + num2  + num3 + " " + set2 + " " + set3;
    }

    @Step("Generating order data")
    public static Map <String, String> generateOrderData() {

        HashMap <String, String> orderData = new HashMap <>();

        orderData.put("firstName", RandomStringUtils.randomAlphabetic(10));
        orderData.put("lastName",  RandomStringUtils.randomAlphabetic(10));
        orderData.put("address", RandomStringUtils.randomAlphabetic(10));
        orderData.put("metroStation", String.valueOf(RandomUtils.nextInt(1, 9)));
        orderData.put("phone", generatePhone());
        orderData.put("rentTime", String.valueOf(RandomUtils.nextInt(1, 9)));
        orderData.put("deliveryDate", LocalDateTime.now().plusDays(RandomUtils.nextInt(1, 30)).toString());
        orderData.put("comment", RandomStringUtils.randomAlphabetic(50));

        return orderData;
    }
}