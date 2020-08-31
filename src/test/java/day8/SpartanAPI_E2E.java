package day8;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import javafx.application.Application;
import org.junit.jupiter.api.*;

import java.io.File;
import java.io.FileInputStream;

import static org.hamcrest.Matchers.is;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SpartanAPI_E2E {
    static int id;

    @BeforeAll
    public static void setUp() {

        RestAssured.baseURI = "http://52.3.242.24";
        RestAssured.port = 8000;
        RestAssured.basePath = "/api";

    }

    @Order(1)
    @Test
    public void testAddData() {

        File jsonFile = new File("spartan.json");
        Response response=
        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(jsonFile).

                when()
                .post("/spartans");



       id= response.jsonPath().getInt("data.id");



    }

    @Order(2)
    @Test
    public void testReadData() {

        RestAssured.given()
                .log().all()
                .pathParam("id",id)

                .when()
                .get("spartans/{id}").prettyPeek().
                then()
        .statusCode(is(200));

    }

    @Order(3)
    @Test
    public void testUpdateData() {


    }

    @Order(4)
    @Test
    public void testDeleteData() {


    }
}
