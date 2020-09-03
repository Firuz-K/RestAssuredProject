package day9;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import POJO.Spartan;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import io.restassured.http.ContentType;


public class SpartanRoleBasedAccessControlNegativeTest {

    static Spartan spartanPojo;

    @BeforeAll
    public static void setUp() {

        //we are using Akbar's ip address because it is with basic auth,
// and my ip is with no auth, so i will not be able to complete the test with my own
        baseURI = "http://54.160.106.84";
        port = 8000;
        basePath = "/api";
        /*
        DB_Utility.createConnection(ConfigurationReader.getProperty("spartan1.database.url"),
                ConfigurationReader.getProperty("spartan1.database.username"),
                ConfigurationReader.getProperty("spartan1.database.password"));

        */
    }


    @DisplayName("User should not be able to start with delete data")
    @Test
    public void testUserCanNotDelete(){

        given()
               .auth().basic("user","user")
                .accept(ContentType.JSON)
                .log().all().

                when()
                .delete("/spartans/{id}",10).

                then()
                .statusCode(403)
                .header("Date",is(notNullValue()))
                .log().all();




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
