package Day5;

import POJO.Countries;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

public class CountriesSerialization {



    @BeforeAll
    public static void setUp(){
        RestAssured.baseURI="http://52.3.242.24";
        RestAssured.port=1000;
        RestAssured.basePath="/ords/hr";
    }


    @Test
    public void testingCountries(){

        Response response=
                RestAssured.given()
                .log().all()
                .accept(ContentType.JSON).
                        when()
                .get("/countries/{country_id}","CH").prettyPeek();


        JsonPath jp = response.jsonPath();

      // List<String> listOfCountryNames = jp.getList("items.country_name");

        System.out.println("=====================================");

       // System.out.println(listOfCountryNames);

        Countries countries = response.as(Countries.class);
        System.out.println(countries);



    }
}
