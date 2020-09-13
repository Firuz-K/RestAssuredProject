import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.is;

public class MovieDBXML {


    @BeforeAll
    public static void setUp() {
        RestAssured.baseURI = "http://www.omdbapi.com/";

    }


    @Test
    public void testingMovieDB() {
         RestAssured.given()
                .accept(ContentType.JSON)
                .queryParam("apikey", "26aa5b74")
                .queryParam("t", "Titanic")
                .queryParam("r", "xml").

                        when()
                .get().prettyPeek().

                then().statusCode(200)
                .contentType(ContentType.XML)
                .body("root.movie.@title",is("Titanic"))
                 .body("root.movie.@released",is("19 Dec 1997"))
         .body("root.@response",is("True"))
         ;


    }
}
