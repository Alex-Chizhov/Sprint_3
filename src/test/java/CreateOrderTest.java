import clients.OrderClient;
import data.OrderDataGenerator;
import io.qameta.allure.junit4.DisplayName;
import models.OrderModel;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.util.List;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.hasKey;


@DisplayName("Create order")
@RunWith(Parameterized.class)
public class CreateOrderTest {

    private final OrderClient orderClient = new OrderClient();
    private final OrderModel orderModel;
    private int track = 0;


    public CreateOrderTest(OrderModel orderModel) {
        this.orderModel = orderModel;
    }

    @Parameterized.Parameters(name="{index} Test data: {0}")
    public static Object[][] getData() {
        return new Object[][]{
                {new OrderModel(OrderDataGenerator.generateOrderData(), List.of("BLACK"))},
                {new OrderModel(OrderDataGenerator.generateOrderData(), List.of("GREY"))},
                {new OrderModel(OrderDataGenerator.generateOrderData(), List.of("BLACK", "GREY"))},
                {new OrderModel(OrderDataGenerator.generateOrderData(), List.of())}
        };
    }

    @Test
    @DisplayName("Creating an order with color")
    public void createOrderTest() {
        track = orderClient.send(orderModel).
                then()
                .statusCode(201)
                .body("$", hasKey("track"))
                .body("track", notNullValue())
                .extract().body().path("track");
    }

    @After
    @DisplayName("Close order")
    public void closeOrder() {
        if (track != 0) orderClient.cancel(track);
    }
}
