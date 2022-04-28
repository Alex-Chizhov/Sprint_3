package models;
import java.util.List;

public class OrdersModel {

    private List<OrderModel> orders;

    public OrdersModel() {
    }

    public OrdersModel(List<OrderModel> orders) {
        this.orders = orders;
    }

    public List<OrderModel> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderModel> orders) {
        this.orders = orders;
    }
}
