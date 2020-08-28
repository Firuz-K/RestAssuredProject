package day4;

import POJO.Spartan;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class TestWithPojo {

    @BeforeAll
    public static void setUp(){
        baseURI="http://52.3.242.24";
        port=8000;
        basePath="/api";
    }


    @Test
    public void testingWithPojo(){

        Spartan spartan = new Spartan("Pojo","Female",12312312456L);

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(spartan).

                when()
                .post("/spartans").

                then()
                .log().all()
                .statusCode(is(201));

    }

}
