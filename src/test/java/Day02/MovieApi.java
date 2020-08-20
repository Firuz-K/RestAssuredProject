package Day02;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.*;

public class MovieApi {

    @BeforeAll
    public static void init(){
        RestAssured.baseURI="http://www.omdbapi.com";

    }

    @Test
    public void testMovies(){
        RestAssured.given()
                .log().all()
                .queryParam("apikey","b602d6ca")
                .queryParam("t","Boss Baby")
                .queryParam("plot","full").

                when()
                .get().

                then()

                .statusCode(200)
                .log().all()
        .body("Title",containsString("Baby"))
                ;


    }
}
