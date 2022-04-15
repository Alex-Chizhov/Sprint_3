import clients.CourierClient;
import data.CourierDataGenerator;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.HashMap;
import java.util.Map;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.hasKey;


@DisplayName("Login courier")
public class LoginCourierTest {

    private final CourierClient courierClient = new CourierClient();
    private Map <String, String> courierData;

    @Before
    @DisplayName("Generating courier data and create courier")
    public void createTestData() {
        courierData = CourierDataGenerator.generateCourierData();
        courierClient.create(courierData);
    }

    @Test
    @DisplayName("Courier authorization")
    public void courierAuthorization() {
        Response response = courierClient.login(courierData);
        response
                .then()
                .statusCode(200)
                .body("$", hasKey("id"))
                .body("id", notNullValue());
    }

    @Test
    @DisplayName("Authorization courier without login")
    public void authorizationCourierWithoutLoginTest() {
        Map<String, String> courierDataMap = new HashMap<>(courierData);
        courierDataMap.remove("login");
        Response response = courierClient.login(courierDataMap);
        response
                .then()
                .statusCode(400)
                .body("message", equalTo("Недостаточно данных для входа"));
    }

    @Test
    @DisplayName("Authorization courier without password")
    public void authorizationCourierWithoutPasswordTest() {
        Map<String, String> courierDataMap = new HashMap<>(courierData);
        courierDataMap.remove("password");
        Response response = courierClient.login(courierDataMap);
        response
                .then()
                .statusCode(400)
                .body("message", equalTo("Недостаточно данных для входа"));
    }

    @Test
    @DisplayName("Authorization courier with invalid login and password")
    public void authorizationCourierWithInvalidLoginAndPassword() {
        Map<String, String> courierDataMap = new HashMap<>(courierData);
        courierDataMap.put("login", "qwerty");
        courierDataMap.put("password", "1111");
        Response response = courierClient.login(courierDataMap);
        response
                .then()
                .statusCode(404)
                .body("message", equalTo("Учетная запись не найдена"));
    }

    @After
    @DisplayName("Removing courier")
    public void removingCourier() {
        int id = courierClient.getCourierId(courierData);
        if(id != 0) courierClient.remove(String.valueOf(id));
    }
}
