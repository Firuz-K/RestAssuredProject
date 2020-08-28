package day3;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

public class SpartanSearch {

    @BeforeAll
    public static void setUp(){
        RestAssured.baseURI="http://52.3.242.24";
        RestAssured.port=8000;
        RestAssured.basePath="/api";
    }


    @DisplayName("Searching Spartan")
    @Test
    public void test1(){

        Response response= RestAssured.given()

                .log().all()
                .queryParam("gender","Female").

                when()
                .get("/spartans/search")
                .prettyPeek();

        JsonPath jp =response.jsonPath();

       int numberOfSpartans= jp.getInt("numberOfElements");
        System.out.println(numberOfSpartans);


        List<String> listOfId = jp.getList("content.name");
        System.out.println(listOfId);











    }

}
