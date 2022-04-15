package clients;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.given;

public class CourierClient extends BaseClient {

    private final String ENDPOINT = "/api/v1/courier";

    @Step("Create courier")
    public Response create(Map<String, String> data) {
        return given()
                .header("Content-type", JSON)
                .and()
                .body(data)
                .when()
                .post(BASEURL + ENDPOINT);
    }

    @Step("Courier login")
    public Response login(Map<String, String> param) {
        return given()
                .header("Content-type", JSON)
                .and()
                .body(param)
                .when()
                .post(BASEURL + ENDPOINT + "/login");
    }

    @Step("Removing courier")
    public Response remove(String CourierId) {
        return given().delete(BASEURL+ ENDPOINT + "/{CourierId}", CourierId);
    }

    @Step("Get courier id")
    public int getCourierId(Map<String, String> courierData) {
        HashMap<String, String> loginData = new HashMap<>(courierData);
        loginData.remove("firstName");

        Response response = login(loginData);
        int id = 0;
        int statusCode = response.statusCode();
        if ( statusCode == 200) {
            id = response
                    .then()
                    .extract()
                    .body()
                    .path("id");
        }
        return id;
    }
}
