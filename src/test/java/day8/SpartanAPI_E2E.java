package day8;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import java.io.File;

import static org.hamcrest.Matchers.is;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SpartanAPI_E2E {
    static int id;

    @BeforeAll
    public static void setUp() {

        RestAssured.baseURI = "http://52.3.242.24";
        RestAssured.port = 8000;
        RestAssured.basePath = "/api";

    }

    @Order(1)
    @Test
    public void testAddData() {

        File jsonFile = new File("spartan.json");
        Response response=
        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(jsonFile).

                when()
                .post("/spartans");



       id= response.jsonPath().getInt("data.id");

        System.out.println("Newly created id is = "+id);



    }

    @Order(2)
    @Test
    public void testReadData() {

        RestAssured.given()
                .log().all()
                .pathParam("id",id)

                .when()
                .get("spartans/{id}").prettyPeek().
                then()
        .statusCode(is(200));
        System.out.println("Getting newly created id = "+id);

    }

    @Order(3)
    @Test
    public void testUpdateData() {

        System.out.println("Updating spartan with ID = "+id);

        File file = new File("updatedBody.json");

        RestAssured.given()

                .contentType(ContentType.JSON)
                .pathParam("id",id)
                .body(file).log().all().

                when()
                .put("/spartans/{id}").

                then()
                .statusCode(is(204));


    }

    @Order(4)
    @Test
    public void testReadUpdatedData() {

        RestAssured.given()
                .log().all()
                .pathParam("id",id)

                .when()
                .get("spartans/{id}").prettyPeek().
                then()
                .statusCode(is(200));
        System.out.println("Spartan with id = "+id+" is updated ");

    }

    @Order(5)
    @Test
    public void testDeleteData() {

        RestAssured.given().log().all()
                .pathParam("id",id )
                .when()
                .delete("spartans/{id}").

                then()
                .statusCode(is(204));


    }
}
