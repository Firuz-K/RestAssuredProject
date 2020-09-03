package day9;

import POJO.Spartan;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.notNullValue;


public class SpartanRoleBasedAccessControlNegativeTest_reuse {

    static Spartan spartanPojo;
    static RequestSpecification rs;
    static ResponseSpecification responseSpecification;

    @BeforeAll
    public static void setUp() {


        // building reusable request specification using RequestSpecBuilderClass

        //we are using Akbar's ip address because it is with basic auth,
// and my ip is with no auth, so i will not be able to complete the test with my own
        baseURI = "http://54.160.106.84";
        port = 8000;
        basePath = "/api";



    }


    @DisplayName("User should not be able to start with delete data")
    @Test
    public void testUserCanNotDelete(){

        rs = given()
                .auth().basic("user","user")
                .accept(ContentType.JSON)
                .log().all();

        ResponseSpecBuilder rsb = new ResponseSpecBuilder();

        //getting reusable Response Spec Object using the builder method
        responseSpecification= rsb.expectStatusCode(403)
                .expectContentType(ContentType.JSON)
                .expectHeader("Date",notNullValue(String.class))
                .log(LogDetail.ALL)
                .build();


        given()
               .spec(rs).

                when()
                .delete("/spartans/{id}",10).

                then()
                .spec(responseSpecification);




    }

    @DisplayName("User should not be able to start with delete data")
    @Test
    public void testUserCanNotUpdate(){

         spartanPojo = new Spartan("Name","Female",323232323232L);

        given()
                .auth().basic("user","user")
                .accept(ContentType.JSON)
                .body(spartanPojo).

                when()
                .put("/spartans/{id}",10).

                then()
                .header("Date",is(notNullValue()))
                .statusCode(403)
                .log().all();

    }


    @DisplayName("User should not be able to start with delete data")
    @Test
    public void testUserCanNotPost(){

         spartanPojo = new Spartan("Name","Female",323232323232L);
        given()
                .auth().basic("user","user")
                .accept(ContentType.JSON)
                .body(spartanPojo)
                .log().all().

                when()
                .post("/spartans").

                then()
                .statusCode(403)
                .header("Date", is(notNullValue()))
                .log().all();

    }


    @Test
    public void testUserAccess(){


        given()
                .auth().basic("user","user")
                .accept(ContentType.JSON).


                when()
                .get("/spartans/{id}",10).

                then()
                .header("Date",is(notNullValue()))
                .statusCode(200)
                .log().all();

    }



}
