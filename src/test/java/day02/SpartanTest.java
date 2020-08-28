package day02;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class SpartanTest {

    @DisplayName("Get All Spartans")
    @Test
    public void testAllSpartans() {
        String url = "http://52.3.242.24:8000/api/spartans";

        baseURI = "http://52.3.242.24:8000"; // comes from RestAssured --> RestAssured.baseURI
        basePath = "/api";   // comes from RestAssured --> RestAssured.basePath

        given()
                .header("Accept", "Application/json").

                when()
                .get("/spartans/126").prettyPeek().


                then()
                .statusCode(200);
    }


    @DisplayName("Get All Spartans")
    @Test
    public void testAllSpartans2() {

        // send the same request specifying the accept header is application/json
        // use baseURI and basePath, check status code 200, content type header is json

       given()
                // .accept("application/json").
                .accept(ContentType.JSON).  // easiest way to set ContentType

        when()
                .get("/spartans").
               prettyPeek().

        then()
                .statusCode(is(200))
                //  .contentType("application/json;charset=UTF-8")
                //.contentType(is("application/json;charset=UTF-8"))
                .contentType(ContentType.JSON); // easiest way using ContentType enum


    }

    @BeforeAll
    public static void settingURI() {
               RestAssured.baseURI=("http://52.3.242.24:8000");
                RestAssured.basePath=("/api");
    }

    @DisplayName("Get one spartan")
    @Test
    public void testSingleSpartan(){

        // i want to log the request I sent so Isee what is URL
        given()
                .log().all().

       when()
               .get("/spartans/128").
             //  prettyPeek().

                then()
                .statusCode(200)
                .log().all();
                //.log().all();
              // .log().body();
    }


    @DisplayName("Hello from Sparta test")
    @Test
    public void hello(){

        given()
                .accept(ContentType.TEXT).

                when()
                .get("/hello").

                then()
                .statusCode(200)
                .contentType(ContentType.TEXT)
                .body(equalTo("Hello from Sparta"));

    }
}
