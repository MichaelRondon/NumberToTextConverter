package com.mfra.service.integration;

import static io.restassured.RestAssured.*;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import static org.hamcrest.CoreMatchers.equalTo;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category(ConverterIntegrationTest.class)
public class ConvertIntegrationTest {

    @Test
    public void happyPath() {
        String uri = "/convert/-" + Long.MAX_VALUE;

        given().header(new Header("Origin", "http://localhost:8080"))

        .when()
                .get(uri)
                .then()
                .assertThat().statusCode(200)
                .assertThat().contentType(ContentType.JSON)
                .body("output", equalTo("Minus nine trillion two hundred twenty three thousand three hundred seventy two billion thirty six thousand eight hundred fifty four million seven hundred seventy five thousand eight hundred seven"))
        ;
    }

    @Test
    public void wrongInput() {
        String uri = "/convert/-12543341234567890987654321";

        given().header(new Header("Origin", "http://localhost:8080"))

        .when()
                .get(uri)
                .then()
                .assertThat().statusCode(400)
                .assertThat().contentType(ContentType.JSON)
                .body("message", equalTo("Error invoking the service. For input string: \"-12543341234567890987654321\". Required input: class java.lang.Long"))
                .body("request", equalTo("uri=/convert/-12543341234567890987654321"))
        ;
    }

    @Test
    public void wrongInput2() {
        String uri = "/convert/" + Long.MIN_VALUE;

        given().header(new Header("Origin", "http://localhost:8080"))

        .when()
                .get(uri)
                .then()
                .assertThat().statusCode(400)
                .assertThat().contentType(ContentType.JSON)
                .body("message", equalTo("Some errors were found in the request:\nthe minimun allowed value is -9.223.372.036.854.775.807 Invalid value: -9223372036854775808"))
                .body("request", equalTo("uri=/convert/-9223372036854775808"))
        ;
    }

    @Test
    public void wrongInputFloat() {
        String uri = "/convert/1.1";

        given().header(new Header("Origin", "http://localhost:8080"))

        .when()
                .get(uri)
                .then()
                .assertThat().statusCode(400)
                .assertThat().contentType(ContentType.JSON)
                .body("message", equalTo("Error invoking the service. For input string: \"1.1\". Required input: class java.lang.Long"))
                .body("request", equalTo("uri=/convert/1.1"))
        ;
    }

}