package day02;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.hamcrest.Matchers.*;

public class BreakingBad {

    @BeforeAll
    public static void setUp(){

        RestAssured.baseURI="https://www.breakingbadapi.com";
        RestAssured.basePath="/api";
    }

    @Test
    public void breakingBad(){

        RestAssured.given()
                .log().all()
                .queryParam("name","Salamanca").

        when()
                .get("/characters").

        then()
                .log().all()
                .statusCode(200)
                .body("occupation[0][0]", is("Former Senior Cartel Leader"))
                .body("portrayed[-1]",is("Tony Dalton"))
                .body("nickname[-2]",is("The Cousins"))
                .body("name[1]",is("Tuco Salamanca"))
                .body("occupation[1][0]",is("Meth Distributor"))
                ;
    }

}
