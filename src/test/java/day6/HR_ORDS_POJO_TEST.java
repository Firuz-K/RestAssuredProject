package day6;

import POJO.Locations;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class HR_ORDS_POJO_TEST {


    @BeforeAll
    public static void setUp(){
        RestAssured.baseURI="http://52.3.242.24";
        RestAssured.port=1000;
        RestAssured.basePath="/ords/hr";
    }



    @Test
    public void test(){


        Response response=
                RestAssured.given()
                .pathParam("location_id",1700)
                .log().all()
                .accept(ContentType.JSON).
                        when()
                .get("/locations/{location_id}").prettyPeek();

        System.out.println("===========================================");
        Locations locations = response.as(Locations.class);
        System.out.println("locations is = "+locations);
    }

}
