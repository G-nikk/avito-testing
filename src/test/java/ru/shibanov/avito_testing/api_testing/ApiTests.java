package ru.shibanov.avito_testing.api_testing;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.hamcrest.Matchers.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class ApiTests {
    private final String BASE_URL = "https://qa-internship.avito.com/api/1";
    private final String ITEM_ID = "7a8fe969-2a57-468e-82c9-1982d22023c5";
    private final String WRONG_ITEM_ID = "7a8fe969-2a57-468e-82c9-1982d22023c4";

    @ParameterizedTest
    @ValueSource(strings = {"1", "123", "12345"})
    public void getAllItemsBySellerId(String sellerId) {
        RestAssured
                .get(BASE_URL + "/" + sellerId + "/item")
                .then()
                .assertThat()
                .statusCode(200)
                .and()
                .contentType(ContentType.JSON)
                .body("sellerId", hasItem(Integer.valueOf(sellerId)))
                .and()
                .body(matchesJsonSchemaInClasspath("schemas/GetAllItemsBySellerId.json"));
    }

    @Test
    public void getItemById() {
        RestAssured
                .get(BASE_URL + "/item/" + ITEM_ID)
                .then()
                .assertThat()
                .statusCode(200)
                .and()
                .contentType(ContentType.JSON)
                .body(matchesJsonSchemaInClasspath("schemas/GetItemByIdResponse.json"));
    }

    @Test
    public void getItemByWrongId() {
        RestAssured
                .get(BASE_URL + "/item/" + WRONG_ITEM_ID)
                .then()
                .assertThat()
                .statusCode(404)
                .and()
                .contentType(ContentType.JSON)
                .body("status", equalTo("404"));
    }


}
