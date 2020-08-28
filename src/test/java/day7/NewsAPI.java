package day7;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
public class NewsAPI {

    //String APIKey ="5192324ec04448959a5726719d68aa43";

    @BeforeAll
    public static void setUp(){
        baseURI="https://newsapi.org";
        basePath="/v2";
    }


    @DisplayName("News API get all authors if the source id is not null")
    @Test
    public void test(){

        String token = "5192324ec04448959a5726719d68aa43";
        Response response=
        given().
                header("Authorization","Bearer "+ token)
                .queryParam("country","us").log().all()
                .when()
                .get("/top-headlines");

        JsonPath jp = response.jsonPath();
        List<String> authors =jp.getList("articles.author");

        List<String> allAuthors = jp.getList("articles.findAll{it.source.id !=null}.author");
        System.out.println("====================================");
        System.out.println("Authors size "+authors.size());
        System.out.println("Filtered authors "+allAuthors.size());





    }
}
