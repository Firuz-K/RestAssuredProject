import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
public class Misctest {



    @BeforeAll
    public  static void setUp(){
        baseURI="http://www.omdbapi.com/";
    }



    @Test
    public void MovieTest(){

        Response response=
        given().
                queryParam("apikey","26aa5b74").
                queryParam("t","The Terminator")

                .log().all()

                .when()
                .get().prettyPeek();

        System.out.println("response.jsonPath().getString(\"Title\") = " + response.jsonPath().getString("Title"));

    }
}
