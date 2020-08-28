package day5;

import POJO.Locations;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

public class LocationsSerialization {

    @BeforeAll
    public static void setUp(){
        RestAssured.baseURI="http://52.3.242.24";
        RestAssured.port=1000;
        RestAssured.basePath="/ords/hr";
    }

    @Test
    public void locations() {

        Response response =

                RestAssured.given()

                        .log().all()
                        .accept(ContentType.JSON).
                        when()
                        .get("/locations").
                        prettyPeek();

      JsonPath jp = response.jsonPath();

        //List<Locations> streetAddress = jp.getList("items", Locations.class);
       //String PostalCode =jp.getString("items[0].postal_code");

      // List<String> postalCodes = jp.getList("items.postal_code");
        System.out.println("=======================================================");
        //System.out.println("Postal code is -->"+PostalCode);
      //  System.out.println("The list of postal_codes-->"+postalCodes);

        List<Locations> locationsList = jp.getList("items",Locations.class);

        System.out.println(locationsList);


    }
}
