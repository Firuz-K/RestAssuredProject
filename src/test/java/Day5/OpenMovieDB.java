package Day5;

import POJO.MovieDB;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
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
                .queryParam("s","Superman").

                when()
                .get().prettyPeek();

        JsonPath jp = response.jsonPath();

       // MovieDB movieDB=response.as(MovieDB.class);
     // List<String> listOfTitle = jp.getList("Search.Title");
        List<MovieDB> allMovies=jp.getList("Search");

        //List<String> allMovies=jp.getList("Search.Year");
        System.out.println("=========================================");
        System.out.println(allMovies);
        System.out.println("allMovies.size() = " + allMovies.size());


    }
}
