package Day5;

import POJO.Spartan;
import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


public class SecureSpartanTest {

    @BeforeAll
    public static void setup(){
        RestAssured.baseURI="http://54.160.106.84";
        RestAssured.port=8000;
        RestAssured.basePath="/api";
    }


    @Test
    public void gettingSpartanWithoutCredentials(){
        RestAssured.given()

                .log().all().


                when()
                .get("/spartans/{{id}}",100).

                then().statusCode(401).log().all();
    }

    @Test
    public void gettingSpartanWithCredentials(){
        RestAssured.given()

                .log().all()
                .auth().basic("admin","admin").


         when()
                .get("/spartans/{id}",188).

                then().statusCode(200).log().all();
    }


    public static int createRandomSpartan(){
        String name= new Faker().name().firstName();
        String gender =new Faker().demographic().sex();
        long phone = new Faker().number().numberBetween(1000000000L,9999999999L);

        Spartan spartan = new Spartan(name,gender,phone);

        Response response=
        RestAssured.given()
                .log().ifValidationFails()
                .auth().basic("admin","admin")
                .body(spartan)
                .contentType(ContentType.JSON).

        when()
                .post("/spartans")
                .prettyPeek();

        return response.jsonPath().getInt("data.id");
    }

    @Test
    public void testingIntSpartan(){

        int id =createRandomSpartan();

        System.out.println("ID is --> "+id);
    }



}
