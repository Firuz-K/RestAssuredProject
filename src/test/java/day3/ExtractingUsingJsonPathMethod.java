package day3;

import  static io.restassured.RestAssured.*;

import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;
import   io.restassured.response.*;

import java.util.List;
import java.util.Map;

public class ExtractingUsingJsonPathMethod {


    @Test
    public void test1() {

        Response response =
                given()
                        .baseUri("http://www.omdbapi.com")
                        .queryParam("apikey", "b602d6ca")
                        .queryParam("t", "Boss Baby").
                        //.queryParam("plot","full").
                         log().all().

                when()
                        .get();
        // we get the object by using method called .jsonPath();
        JsonPath jp =response.jsonPath();

        String a =jp.getString("Title");
        System.out.println(a);

        int year = jp.getInt("Year");
        System.out.println("Year is -- "+year);

        Map<String,Object> responseMap =jp.getMap("");
        System.out.println(responseMap);

        // print out above key from Map

        System.out.println("Awards are "+responseMap.get("Awards"));

       Map<String,Object> ratingsMap = jp.getMap("Ratings[0]");
        System.out.println(ratingsMap);

        // saving our result in the List
        List <String> sourceList=jp.getList("Ratings.Source");
        System.out.println("The List is -- "+sourceList);





    }
}
