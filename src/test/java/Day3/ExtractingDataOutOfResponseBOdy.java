package Day3;

import io.restassured.RestAssured;
import  static io.restassured.RestAssured.*;
import org.junit.jupiter.api.Test;
import   io.restassured.response.*;

import static org.hamcrest.Matchers.*;

public class ExtractingDataOutOfResponseBOdy {




    @Test
    public void test1(){

        Response response =
        given()
                .baseUri("http://www.omdbapi.com")
                .queryParam("apikey","b602d6ca")
                .queryParam("t","Boss Baby").
                //.queryParam("plot","full").
                        log().all().

                when()
                .get();




        response.prettyPrint();


       int code= response.statusCode();
        System.out.println(code);

       String title= response.path("Title");
        System.out.println("Title is -- "+title);

        System.out.println("response.path(\"Year\") = " + response.path("Year"));
        System.out.println("response.path(\"Director\") = " + response.path("Director"));
        System.out.println("response.path(\"Year\") = " + response.path("Year"));
        System.out.println("response.path(\"Ratings[0].Source\") = " + response.path("Ratings[0].Source"));
        System.out.println("response.path(\"Metascore\") = " + response.path("Metascore"));




    }
}
