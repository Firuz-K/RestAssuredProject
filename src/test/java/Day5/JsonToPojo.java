package Day5;

import POJO.Spartan2;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

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

        Response response=
        RestAssured.given()
                .auth().basic("admin","admin")
                .log().all().

                when()
                .get("/spartans/{id}",317).prettyPeek();

        // as method from response accepts a type to define what is the type of object
        // you are trying to store the response as
        Spartan2 sp = response.as(Spartan2.class);

        System.out.println("sp = "+sp);

    }


}
