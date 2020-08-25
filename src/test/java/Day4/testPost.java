package Day4;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class testPost {




    @BeforeAll
    public static void setUp(){
        baseURI="http://54.160.106.84";
        port=8000;
        basePath="/api";
    }

    @Test
    public void postingJsonFile(){

      //  File file = new File("spartan.json");


        Map<String,Object> file= new HashMap<String, Object>();

        file.put("name","Jackson");
        file.put("gender","Male");
        file.put("phone",123412341234L);

        RestAssured.given()

                .log().all()
                .contentType(ContentType.JSON)
                .body(file).

                when()
                .post("/spartans")

                .then()
                .log().all()
                .statusCode(201);



    }


    @Test
    public void test(){

        RestAssured.given()
                .auth().basic("admin","admin")
                .log().all()

                .when()
                .get("/spartans").then().log().all();
    }

}
