package day9;

import POJO.Spartan;
import com.github.javafaker.Faker;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class PostingSpartanReqRespSpec {
//static ResponseBuilder builder;
static ResponseSpecification resSpec;
static RequestSpecification reqSpec;
    @BeforeAll
    public static void setUp() {

        Spartan spartan=createRandomSpartanObject();

        baseURI = "http://54.160.106.84";
        port = 8000;
        basePath = "/api";
        reqSpec= given().auth().basic("admin","admin")
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(spartan)
                .log().all();

        ResponseSpecBuilder builder= new ResponseSpecBuilder();



       resSpec= builder.expectStatusCode(201)
                .expectBody("success",is("A Spartan is Born!"))
                .expectHeader("Date",notNullValue(String.class))
                .expectBody("data.name", is(spartan.getName()))
                .expectBody("data.gender",is(spartan.getGender()))
                .expectBody("data.phone",is(spartan.getPhone()))
                .log(LogDetail.ALL)
                .build();
    }

    @DisplayName("Extracting requestSpec and responseSpec")
    @Test
    public void test() {



        given()
                .spec(reqSpec).


        when()
                .post("/spartans").

                then()
                .spec(resSpec);




    }


    public static Spartan createRandomSpartanObject() {
        Faker faker = new Faker();
        String name   = faker.name().firstName();
        String gender = faker.demographic().sex();
        // always getting long number outside range of int to avoid errors
        long phone    = faker.number().numberBetween(5000000000L,9999999999L);
        Spartan randomSpartanObject = new Spartan(name,gender,phone);
        System.out.println("Created Random Spartan Object : " + randomSpartanObject);
        return randomSpartanObject ;
    }

}
