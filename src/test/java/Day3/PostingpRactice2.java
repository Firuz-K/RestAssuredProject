package Day3;

import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import  io.restassured.path.json.JsonPath;

import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PostingpRactice2 {


    @BeforeAll
    public static void setUp(){
        baseURI="http://52.3.242.24";
        port=8000;
       basePath="/api";
    }

    @DisplayName("Posting")
    @Test
    public void test1(){

        String myBodyData = "{\n" +
                "    \"name\": \"Firuz\",\n" +
                "    \"gender\": \"Male\",\n" +
                "    \"phone\": 5555555555\n" +
                "}";

        Response response =
        given()
                .log().all()
                .body(myBodyData)
                .contentType(ContentType.JSON).

                when()
                .post("/spartans")
                .prettyPeek()


                ;

       int id= response.path("data.id");
        System.out.println("Id is "+id);

        String name = response.path("data.name");
        System.out.println("Name is "+name);

        JsonPath jp=response.jsonPath();

        System.out.println(" ID using jasonPath " + jp.getInt("data.id"));
        System.out.println("Name using JsonPath  " + jp.getString("data.name"));


    }

    @DisplayName("Updating the Data")
    @Test
    public void updating(){

        String myBodyDataUpdated = "{\n" +
                "    \"name\": \"Firuz\",\n" +
                "    \"gender\": \"Male\",\n" +
                "    \"phone\": 5555555555\n" +
                "}";

        given()

                .contentType(ContentType.JSON)
                .body( myBodyDataUpdated).

                when()
                .put("/spartans/{id}",809).

                then()
                .statusCode(204)
                .log().all();



    }
}
