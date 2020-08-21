package Day3;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static org.hamcrest.Matchers.*;

public class Practice1 {


    @BeforeAll
    public static void init(){

        // Setting the port separately from baseURI
        RestAssured.baseURI="http://52.3.242.24";
        RestAssured.port=8000;
        RestAssured.basePath="/api";
    }


    @DisplayName("Test")
    @Test
    public void test1(){

        RestAssured.given()
                .queryParam("gender","Female").
                when()
                .get("/spartans/search").
                then()
                .statusCode(is(200))
        .log().all()
                ;


    }


}
