package Day3;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.*;

public class PostingSpartan {

    @BeforeAll
    public static void setUp(){
        RestAssured.baseURI="http://52.3.242.24";
        RestAssured.port=8000;
        RestAssured.basePath="/api";
    }

    @DisplayName("Testing POST /api/spartans")
    @Test
    public void testAddSpartan(){

        String myBodyData = "{\n" +
                "    \"name\": \"Firuz\",\n" +
                "    \"gender\": \"Male\",\n" +
                "    \"phone\": 5555555555\n" +
                "}";

        //System.out.println(myBodyData);

        RestAssured.given()


                             .contentType(ContentType.JSON)
                             .body(myBodyData)
                             .log().all().

                      when()
                              .post("/spartans").
                      then()
                              .contentType(ContentType.JSON)
                              .statusCode(is(201))
                .log().all()
                .body("success",is("A Spartan is Born!"))
                .body("data.id",is(165))
                .body("data.name",is("Firuz"))
                .body("data.gender",is("Male"))
                .body("data.phone",is(5555555555L));



    }

}
