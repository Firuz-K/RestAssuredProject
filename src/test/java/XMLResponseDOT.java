import  static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.*;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class XMLResponseDOT {




    @BeforeAll
    public static void setUp(){

        baseURI="https://vpic.nhtsa.dot.gov";
        basePath="api/vehicles";

    }


    @Test
    public  void test(){

        given()
                .log().all()
                .queryParam("format","xml")

                .when()
                .get("/GetMakeForManufacturer/988").
                prettyPeek().

                then()
                .statusCode(is(200))
        .body("Response.Message",is("Results returned successfully"))
        .body("Response.Results.MakesForMfg[1].Mfr_Name",is("HONDA OF AMERICA MFG., INC."))
        .body("Response.Results.MakesForMfg[1].Make_ID",is("475"))
        .contentType(ContentType.XML)
        ;
    }
}
