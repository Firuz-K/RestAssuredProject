package day5;

import POJO.MovieDB;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

public class OpenMovieDB {


    @BeforeAll
    public static void setUp(){
        RestAssured.baseURI="http://www.omdbapi.com/";

    }


    @Test
    public void testingMovieDB(){
       Response response = RestAssured.given()
               .accept(ContentType.JSON)
                .queryParam("apikey","26aa5b74")
                .queryParam("s","Breaking bad").

                when()
                .get().prettyPeek();

        //MovieDB movieDB= response.jsonPath().getObject("Search",MovieDB.class);

       // JsonPath jp = response.jsonPath();
        List<MovieDB> listOfMovies=response.jsonPath().getList("Search",MovieDB.class);

        List<String> listString = response.jsonPath().getList("Search.Title");
       // MovieDB movieDB=response.as(MovieDB.class);
     // List<String> listOfTitle = jp.getList("Search.Title");
        //List<MovieDB> allMovies=jp.getList("Search");

        //List<String> allMovies=jp.getList("Search.Year");
        System.out.println("=========================================");
        System.out.println(listString);
        //listString.forEach(each-> System.out.println(each));

        for(String each: listString){
            System.out.println(each);
        }
        //listOfMovies.forEach(each->  System.out.println(each));

     //  System.out.println(listOfMovies);
       // System.out.println("allMovies.size() = " + allMovies.size());


    }
}
