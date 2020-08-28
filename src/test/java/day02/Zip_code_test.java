package day02;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Zip_code_test {

    @BeforeAll
    public static void setUp(){
        baseURI="http://api.zippopotam.us";
        basePath="/us";

    }

    @DisplayName("Zip to City Test")
    @Test
    public void testZipCode(){

       given()
               .log().all()
               .accept(ContentType.JSON).
               pathParam("zipcode",19114).

       when()
               .get("/{zipcode}").

       then()

               .log().all()
               .statusCode(200)
               .contentType(ContentType.JSON)

               .contentType(ContentType.JSON)
               .body("country", is("United States"))
               .body("'post code'",is("19114"))
               .body("'country abbreviation'",is("US"))
               .body("places[0].'place name'",is("Philadelphia"))
               .body("places[0].longitude",is("-74.999"))
               .body("places[0].state",is("Pennsylvania"))
               .body("places[0].'state abbreviation'",is("PA"))
               .body("places[0].latitude",is("40.0634"));

    }

    @DisplayName("Getting from City to Zip")
    @Test
    public void city_to_zip(){

        given()
                .log().all().
          //      .pathParam("state","PA")
           //     .pathParam("city","Philadelphia").

        when()
                .get("/{state}/{city}","PA","Philadelphia").

        then()
                .statusCode(200)
                .log().all()
                .contentType(ContentType.JSON)
                .body("'country abbreviation'", is("US"))
                .body("places[0].'place name'",is("New Philadelphia"))
        // jasonPath hackf for getting last  item from jsonArray
        // -1 index indicate the last item, only works here not postman

        .body("places[-1].latitude",is("40.0018"))

        ;



    }



}
