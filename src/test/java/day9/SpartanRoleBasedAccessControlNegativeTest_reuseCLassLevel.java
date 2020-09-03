package day9;

import POJO.Spartan;
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


public class SpartanRoleBasedAccessControlNegativeTest_reuseCLassLevel {

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

    }


    @DisplayName("User should not be able to start with delete data")
    @Test
    public void testUserCanNotDelete(){



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
                .spec(rs)
                .contentType(ContentType.JSON)
                .body(spartanPojo).

                when()
                .put("/spartans/{id}",10).

                then()
                .spec(responseSpecification);

    }


    @DisplayName("User should not be able to start with delete data")
    @Test
    public void testUserCanNotPost(){

         spartanPojo = new Spartan("Name","Female",323232323232L);
        given()
               .spec(rs)
                .accept(ContentType.JSON)
                .body(spartanPojo).

                when()
                .post("/spartans").

                then()
                .spec(responseSpecification);

    }




}
