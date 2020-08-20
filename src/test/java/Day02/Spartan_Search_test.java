package Day02;

import io.restassured.RestAssured;
import io.restassured.RestAssured.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.hamcrest.Matchers.*;

public class Spartan_Search_test {

    @BeforeAll
    public static void setUpUrl(){
        RestAssured.baseURI = "http://52.3.242.24:8000" ;
        RestAssured.basePath = "/api" ;
    }

    @Test
    public void spartan_parameter(){

        RestAssured.given()
                .log().all()
                .queryParam("gender","Male")
                .queryParam("nameContains","ea").

                when()
                .get("/spartans/search").

                then()
                .statusCode(200)
                .log().all()
                ;

    }

}
