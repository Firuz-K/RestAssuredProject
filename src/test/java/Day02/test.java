package Day02;

import io.restassured.RestAssured;
import io.restassured.RestAssured.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.*;

public class test {


    @BeforeAll
    public static void setUp(){
        RestAssured.baseURI ="http://www.omdbapi.com";


    }

    @DisplayName("Testing OMDB")
    @Test
    public void testMovies(){

        RestAssured.given()

                .queryParam("apikey","b602d6ca")
                .queryParam("t","The Terminator")
                .queryParam("plot","full").

        when()
                .get().

                then()
                .statusCode(is(200))
                .log().all()
        ;


    }
}
