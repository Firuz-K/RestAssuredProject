package Day5;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class LibraryApp {



    @BeforeAll
    public static void setUp(){
        RestAssured.baseURI="http://library2.cybertekschool.com";
        RestAssured.basePath="/rest/v1";
    }

    @Test
    public void libraryApp(){
        Response response=
        RestAssured.given()
                .log().all()
                .contentType(ContentType.URLENC)
                .formParam("email","librarian568@library")
                .formParam("password","SjvLOgHC").
                when()

                .post("/login").prettyPeek();
    }
}
