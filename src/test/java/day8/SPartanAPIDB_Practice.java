package day8;

import com.sun.org.apache.bcel.internal.generic.Select;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import static org.hamcrest.Matchers.is;

public class SPartanAPIDB_Practice {

    /*
    devs just implemented the search endpoint
    and want it to be tested to make sure it's actually returning the correct
    result the correct result form database
     */

    @BeforeAll
    public static void setUp(){
        RestAssured.baseURI="http://52.3.242.24";
        RestAssured.port=8000;
        RestAssured.basePath="/api";
    }

    @Test
    public void test(){
        //make a request to /spartans/search
        // using query parameter gender Female nameContains

        Response response=
        RestAssured.given()
                .log().all()
                .queryParam("gender","Female")
                .queryParam("nameContains","a")

                .when()
                .get("/spartans/search").prettyPeek();

       int count = response.jsonPath().getInt("numberOfElements");
        System.out.println("Count is --> "+count);




    }

    @AfterAll
    public static void destroy(){
        RestAssured.reset();
    }
}
