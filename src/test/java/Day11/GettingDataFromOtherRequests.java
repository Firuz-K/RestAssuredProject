package Day11;

import POJO.Spartan;
import POJO.Spartan2;
import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import utilities.ConfigurationReader;

import java.util.List;
import java.util.Random;

public class GettingDataFromOtherRequests {


    @BeforeAll
    public static void setUp(){

        RestAssured.baseURI= ConfigurationReader.getProperty("spartan1.base_url");
        RestAssured.basePath="/api";
    }

    @Test
    public void test(){

        Response response=
        RestAssured.given()

                .when()
                .get("/spartans");

        JsonPath jp = response.jsonPath();
       List<Spartan2> jpList = jp.getList("", Spartan2.class);
       int max = jpList.size();

       //You can use faker and set random range to gaet random digit
       // Faker faker = new Faker();
        //int random=faker.number().numberBetween(0,max);
       // System.out.println("random = " + random);

        // OR YOU CAN USE RANDOM COMING FROM JAVA.UTIL PACKAGE
        Random random = new Random();

       int random1= random.nextInt(10);

        RestAssured.given().log().all()
                .pathParam("id",random1).

                when()
                .get("/spartans/{id}").prettyPeek();

    }


}
