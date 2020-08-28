package day02;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Spartan {


    @BeforeAll
    public static void setUp(){

        RestAssured.baseURI="http://52.3.242.24:8000";
        RestAssured.basePath="/api";
    }


    @DisplayName("Testing Spartan")
    @Test
    public void spartan_testing(){

       RestAssured.given()
               .log().all()
               .queryParam("id",128).
       when()
               .get("/spartans").
       then()
               .statusCode(200)
               .log().all();

    }
}
