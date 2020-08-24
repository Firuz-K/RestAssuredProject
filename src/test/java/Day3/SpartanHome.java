package Day3;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;

public class SpartanHome {

    @BeforeAll
    public static void setUp(){
        baseURI="http://52.3.242.24";
        port=8000;
        basePath="/api";
    }


    @DisplayName("Getting all and single")
    @Test
    public void gettingSpartan(){
        given()

                .log().all().

                when()
                .get("/spartans/{id}",165).

                then()
                .log().all();
    }


}
