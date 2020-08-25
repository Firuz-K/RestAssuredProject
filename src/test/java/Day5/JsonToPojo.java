package Day5;

import POJO.Spartan2;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

public class JsonToPojo {



    @BeforeAll
    public static void setup(){
        RestAssured.baseURI="http://54.160.106.84";
        RestAssured.port=8000;
        RestAssured.basePath="/api";
    }

    @Test
    public void json2Pojo(){
       // Spartan2 spartan2 = new Spartan2(12,"T-1000","Male",123123123123L);

        int newInt = SecureSpartanTest.createRandomSpartan();

        Response response=
        RestAssured.given()
                .auth().basic("admin","admin")
                .log().all().

                when()
                .get("/spartans/{id}",newInt).prettyPeek();

        // as method from response accepts a type to define what is the type of object
        // you are trying to store the response as
        Spartan2 sp = response.as(Spartan2.class);

        System.out.println("sp = "+sp);

    }


    @Test
    public void gettingNestedJsonAsPojo(){

        Response response=
        RestAssured.given()

                .log().all()
                .auth().basic("admin","admin")
                .queryParam("gender","Male").

                when()
                .get("/spartans/search").prettyPeek();

        System.out.println("response.statusCode() = " + response.statusCode());

       JsonPath jp = response.jsonPath();

        System.out.println("First ID --->  " + jp.getInt("content[0].id"));

        System.out.println("First name -->  "+jp.getString("content[0].name"));

        Spartan2 firstMaleSpartan = jp.getObject("content[0]",Spartan2.class);

       // System.out.println(firstMaleSpartan);

        System.out.println("The Spartan id from POJ0  "+firstMaleSpartan.getId());
        System.out.println("The Spartan name from POJ0  "+firstMaleSpartan.getName());
        System.out.println("The Spartan gender from POJ  "+firstMaleSpartan.getGender());
        System.out.println("The Spartan phone from POJ0  "+firstMaleSpartan.getPhone());

    }

    @DisplayName("Save json array as List<Spartan>")
    @Test
    public void testJsonArrayToListOfPojo(){
        Response response = RestAssured.given()
                .auth().basic("admin","admin")
                .queryParam("gender","Female").

                        when()
                .get("/spartans/search");

        JsonPath jp = response.jsonPath();

        List<String> names = jp.getList("content.name");
        System.out.println(names);
        List<Integer> ids = jp.getList("content.id");
        System.out.println(ids);
        List<String> genders = jp.getList("content.gender");
        System.out.println(genders);
        List<Long> phones = jp.getList("content.phone");
        System.out.println(phones);

        List<Spartan2> spartan2List = jp.getList("content",Spartan2.class);

        System.out.println(spartan2List);
/*
        for(Spartan2 each: spartan2List){
            System.out.println(each);
        }


 */

        System.out.println("=====================================================");

        spartan2List.forEach(each-> System.out.println(each));
    }


}
