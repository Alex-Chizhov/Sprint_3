package clients;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import models.OrderModel;
import models.OrdersModel;
import static io.restassured.RestAssured.given;

public class OrderClient extends BaseClient {

    private final String ENDPOINT = "/api/v1/orders";

    @Step("Cancel order")
    public Response cancel(int track) {
        return given()
                .queryParam("track", String.valueOf(track))
                .put(BASEURL + ENDPOINT + "/cancel");
    }

    @Step("Send order")
    public Response send(OrderModel orderClient) {
        return given()
                .header("Content-type", JSON)
                .and()
                .body(orderClient)
                .when()
                .post(BASEURL + ENDPOINT);
    }

    @Step("Get all orders")
    public OrdersModel getAll() {
        return given()
                .get(BASEURL + ENDPOINT)
                .body()
                .as(OrdersModel.class);
    }
}
