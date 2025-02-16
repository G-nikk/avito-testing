package ru.shibanov.avito_testing.task_2_1;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import net.minidev.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.hamcrest.Matchers.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class ApiTests {
    private final String BASE_URL = "https://qa-internship.avito.com/api/1";
    private final String ITEM_ID = "7a8fe969-2a57-468e-82c9-1982d22023c5";
    private final String WRONG_ITEM_ID = "7a8fe969-2a57-468e-82c9-1982d22023c4";
    private final String STATISTIC_ID = "0cd4183f-a699-4486-83f8-b513dfde477a";
    private final String WRONG_STATISTIC_ID = "0cd4183f-a699-4486-83f8-b513dfde478a";

    @BeforeEach
    public void setup() {
        RestAssured.baseURI = BASE_URL;
    }

    @ParameterizedTest
    @ValueSource(strings = {"1", "12", "12345"})
    public void getAllItemsBySellerIdCorrectResponseBodyCheck(String sellerId) {
        RestAssured
                .get("/" + sellerId + "/item")
                .then()
                .assertThat()
                .statusCode(200)
                .and()
                .contentType(ContentType.JSON)
                .body("sellerId", hasItem(Integer.valueOf(sellerId)))
                .and()
                .body(matchesJsonSchemaInClasspath("schemas/GetAllItemsBySellerIdResponse.json"));
    }

    @Test
    public void getItemByIdCorrectResponseBodyCheck() {
        RestAssured
                .get("/item/" + ITEM_ID)
                .then()
                .assertThat()
                .statusCode(200)
                .and()
                .contentType(ContentType.JSON)
                .body(matchesJsonSchemaInClasspath("schemas/GetItemByIdResponse.json"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"7a8fe969-2a57-468e-82c9-1982d22023c4"})
    public void getItemByWrongIdCorrectStatusCodeCheck() {
        RestAssured
                .get("/item/" + WRONG_ITEM_ID)
                .then()
                .assertThat()
                .statusCode(404)
                .and()
                .contentType(ContentType.JSON)
                .body("status", equalTo("404"));
    }

    @Test
    public void getItemWithoutIdCorrectStatusCodeCheck() {
        RestAssured
                .get("/item/")
                .then()
                .assertThat()
                .statusCode(400)
                .and()
                .contentType(ContentType.JSON)
                .body("code", equalTo(400));
    }

    @ParameterizedTest
    @ValueSource(strings = {"id", "13", "#"})
    public void getItemByWrongIdFormatCorrectStatusCodeCheck(String itemId) {
        RestAssured
                .get("/item/" + itemId)
                .then()
                .assertThat()
                .statusCode(400)
                .and()
                .contentType(ContentType.JSON)
                .body("status", equalTo("400"));
    }

    @Test
    public void postItemCorrectResponseBodyCheck() {
        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(createItemRequestBody())
                .post("/item")
                .then()
                .assertThat()
                .body(matchesJsonSchemaInClasspath("schemas/PostItemResponse.json"));
    }

    @Test
    public void postItemCorrectStatusCodeCheck() {
        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(createItemRequestBody())
                .post("/item")
                .then()
                .assertThat()
                .statusCode(201);
    }

    @Test
    public void getStatisticByIdCorrectResponseBodyCheck() {
        RestAssured
                .get("/statistic/" + STATISTIC_ID)
                .then()
                .assertThat()
                .body(matchesJsonSchemaInClasspath("schemas/GetStatisticByIdResponse.json"));
    }

    @Test
    public void getStatisticByIdCorrectStatusCodeCheck() {
        RestAssured
                .get("/statistic/" + STATISTIC_ID)
                .then()
                .assertThat()
                .statusCode(200);
    }

    @Test
    public void getStatisticByWrongIdCorrectStatusCodeCheck() {
        RestAssured
                .get("/statistic/" + WRONG_STATISTIC_ID)
                .then()
                .assertThat()
                .statusCode(404)
                .and()
                .body("status", equalTo("404"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"12", "@"})
    public void getStatisticByWrongIdFormatCorrectStatusCodeCheck(String id) {
        RestAssured
                .get("/statistic/" + id)
                .then()
                .assertThat()
                .statusCode(400)
                .and()
                .body("status", equalTo("400"));
    }

    private JSONObject createItemRequestBody() {
        JSONObject statistics = new JSONObject();
        statistics.put("contacts", 3);
        statistics.put("likes", 123);
        statistics.put("viewCount", 12);

        JSONObject requestBody = new JSONObject();
        requestBody.put("sellerID", 1234345231);
        requestBody.put("name", "dsds");
        requestBody.put("price", 1);
        requestBody.put("statistics", statistics);

        return requestBody;
    }
}
