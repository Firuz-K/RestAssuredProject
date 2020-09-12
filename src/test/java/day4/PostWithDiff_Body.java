package day4;

import com.github.javafaker.Faker;
import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class PostWithDiff_Body {




    @BeforeAll
    public static void setUp(){
        baseURI="http://52.3.242.24";
        port=8000;
        basePath="/api";
    }


    @Test
    public void testPostWithStringBody(){

        Faker faker= new Faker();

        String randomName=faker.name().firstName();

        //lets try to get random name rather than same Adam each time
        String bodyString="{\n" +
                "    \"name\": \""+randomName+"\",\n" +
                "    \"gender\": \"Male\",\n" +
                "    \"phone\": 5555555555\n" +
                "}";


        given()
                .contentType(ContentType.JSON)
                .body(bodyString).

                when()
                .post("/spartans").


                then().statusCode(is(201)).
                log().all();
    }

    @DisplayName("Posting with external Json file")
    @Test
    public void testPostWithExternalFile(){

        File jsonFile = new File("spartan.json");

        given()

                .contentType(ContentType.JSON)
                .body(jsonFile).

        when()
                .post("/spartans").

        then()
                .statusCode(is(201))
                .log().all()
                .contentType(ContentType.JSON)
                .body("data.name",is("From File"));
    }


    @Test
    public void testWithMapAsBody(){
        Map<String,Object> bodymap = new LinkedHashMap<>();

        bodymap.put("name","Firuz");
        bodymap.put("gender","Male");
        bodymap.put("phone",123123123123L);

        System.out.println(bodymap);

        given()
                .contentType(ContentType.JSON)
                .body(bodymap). // jackson data-bind will turn your java map in to json

                when()
                .post("/spartans").

                then()
                .statusCode(is(201)).
                body("data.name",is("Firuz")).
                log().all();
    }




}
