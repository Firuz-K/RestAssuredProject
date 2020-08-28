package day4;

import POJO.Spartan;
import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Put_and_Patch {

    Faker faker = new Faker();


    @BeforeAll
    public static void setUp(){
        baseURI="http://52.3.242.24";
        port=8000;
        basePath="/api";
    }

    @DisplayName("Put Request body as a Map")
    @Test
    public void testPutRequestWithMap(){

        // put request to update spartan with id 186
        // name: put iwth map, gender: Male, phone:123123123123
        // status code is 204
        // how do I actually know it's updated since it does not have a body in request
        // we can make another get request right after this and assert the body



       /*
        Map<String,Object> updatedBody = new LinkedHashMap<>();
        updatedBody.put("name",randomName);
        updatedBody.put("gender","Male");
        updatedBody.put("phone",55144964991L);

       */

        String randomName = faker.name().firstName();

        // this is how we do pojo request
        Spartan spartan = new Spartan(randomName,"Female",121212121212L);

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(spartan).

                when()
                .put("/spartans/{id}",186).

                then()
                .statusCode(is(204))
                .log().all();
    }


    @Test
    public void gettingSpartan(){

        when().get("/spartans/{id}",186).
        then()
                .statusCode(is(200))
                .log().all();
    }

    @Test
    public void testPatchPartialUpdate(){

        // update only name with faker

        String randomName = faker.name().firstName();

        Map<String,Object> patchBodyMap = new HashMap<>();
        patchBodyMap.put("name",randomName);

        given()
                .log().all()
                .body(patchBodyMap)
                .contentType(ContentType.JSON).

                when()
                .patch("/spartans/{id}",186).

                then()
                .log().all()
                .statusCode(204)
                ;



    }


}
