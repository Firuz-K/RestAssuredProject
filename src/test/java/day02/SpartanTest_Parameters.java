package day02;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class SpartanTest_Parameters {

    @BeforeAll
    public static void setUp(){
        RestAssured.baseURI = "http://54.174.216.245:8000" ;
        RestAssured.basePath = "/api" ;
    }

    @DisplayName("Testing Spartan/{id}")
    @Test
    public void testingSingleSpartan(){

        given()
                .log().all()
                .pathParam("id","971").

                when()

                .get("/spartans/{id}").

                then()
                .statusCode(is(200)).log().all();

    }

    @DisplayName("Testing Spartan2/{id}")
    @Test
    public void testingSingleSpartan2(){

        given()
                .log().all().

        when()
                .get("/spartans/{id}",971).

        then().
                statusCode(is(200))
                .log().all();

    }

    @DisplayName("Testing Body")
    @Test
    public void testSingleSpartanBody(){

        given()
                .log().all().

        when()
                .get("/spartans/{id}",971).

        then()
                .log().all()
                .statusCode(is(200))
                .body("id",is(971))
                .body("name",is("Test"))
                .body("gender",is("Male"))
                .body("phone",is(3216549870l));
               // .body("JSON PATH",is("THE VALUE"))




    }

}