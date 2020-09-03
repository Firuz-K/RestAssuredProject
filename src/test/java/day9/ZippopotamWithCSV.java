package day9;

import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ZippopotamWithCSV {




    @ParameterizedTest
    @CsvFileSource(resources = "/CityAndState.csv",numLinesToSkip = 1)
    public void testZip(String expectedState, String city, int numberOfZipcodes){

        /*System.out.println("expectedState = " + expectedState);
        System.out.println("city = " + city);
        System.out.println("numberOfZipcodes = " + numberOfZipcodes);
         */
        Response response=
        given()
                .baseUri("http://api.zippopotam.us/us")

                .when()
                .get("/{expectedState}/{city}",expectedState,city)
                .prettyPeek();

       List<String> listOfNames = response.jsonPath().getList("places.'place name'");
       String placeName = response.jsonPath().getString("'place name'");

        assertThat(listOfNames.size(),is(numberOfZipcodes));
        assertThat(placeName,is(city));
        assertThat(listOfNames.size(),is(numberOfZipcodes));

        System.out.println("response.jsonPath().getInt(\"places.size()\") = " + response.jsonPath().getInt("places.size()"));


    }

    @Test
    public void testSingle(){

        Response response=
                given()
                        .baseUri("http://api.zippopotam.us/us")

                        .when()
                        .get("/{expectedState}/{city}","PA","New Philadelphia")
                        .prettyPeek();

        response.then().statusCode(200);
    }
}
