import clients.CourierClient;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import data.CourierDataGenerator;
import java.util.HashMap;
import java.util.Map;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.containsString;


@DisplayName("Creating courier")
public class CreateCourierTest {

    private final CourierClient courierClient = new CourierClient();
    private Map <String, String> courierData;


    @Before
    @DisplayName("Generating courier data")
    public void createCourierData() {
        courierData = CourierDataGenerator.generateCourierData();
    }

    @Test
    @DisplayName("Creating courier")
    public void createCourierTest() {
        Response response = courierClient.create(courierData);
        response
                .then()
                .statusCode(201)
                .body("ok", equalTo(true));
    }

    @Test
    @DisplayName("Create copy of the courier with the same data")
    public void createCopyCourierTest() {
        courierClient.create(courierData);
        Response response = courierClient.create(courierData);
        response
                .then()
                .statusCode(409)
                .body("message", containsString("Этот логин уже используется"));
    }

    @Test
    @DisplayName("Create courier without login")
    public void createCourierWithoutLoginTest() {
        Map<String, String> courierDataMap = new HashMap<>(courierData);
        courierDataMap.remove("login");
        Response response = courierClient.create(courierDataMap);
        response
                .then()
                .statusCode(400)
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }

    @Test
    @DisplayName("Create courier without password")
    public void createCourierWithoutPasswordTest() {
        Map<String, String> courierDataMap = new HashMap<>(courierData);
        courierDataMap.remove("password");
        Response response = courierClient.create(courierDataMap);
        response
                .then()
                .statusCode(400)
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }

    @After
    @DisplayName("Removing courier")
    public void removingCourier() {
        int id = courierClient.getCourierId(courierData);
        if(id != 0) courierClient.remove(String.valueOf(id));
    }
}
