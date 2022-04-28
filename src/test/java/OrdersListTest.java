import clients.OrderClient;
import models.OrdersModel;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Orders list")
public class OrdersListTest {

    private final OrderClient orderClient = new OrderClient();

    @Test
    @DisplayName("Get all orders")
    public void getOrdersTest() {
        OrdersModel allOrders = orderClient.getAll();
        assertThat(allOrders.getOrders().size())
                .as("Полученный список заказов пустой").isNotZero();
    }
}
