package BreakingBad;


import POJO.Spartan;
import POJO.Spartan2;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import lombok.ToString;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static io.restassured.module.jsv.JsonSchemaValidator.*;

import java.io.File;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class test1 {


     int id=0;

    @BeforeAll
    public static void setUp(){

        baseURI= "http://52.3.242.24";
        basePath="/api";
        port=8000;


    }

    @Test
    public  void gettingAllSPartans(){

        Response response=
        given().accept(ContentType.JSON).


        when().get("/spartans");


        JsonPath jp = response.jsonPath();

        List<Spartan> spartns=jp.getList("", Spartan.class);

        System.out.println(spartns);

        System.out.println("spartns.get(0) = " + spartns.get(0));

    }

    @DisplayName("Getting one spartan")
    @Test
    public void gettingOneSpartan(){



        given()
                .pathParam("id",10)

                .when().get("spartans/{id}").prettyPeek().
                then()
                .assertThat().statusCode(200).
                assertThat().body(matchesJsonSchemaInClasspath("SIngleJsonSchema.json"));





    }

    @DisplayName("Posting with Spartan")
    @Test
    public void posting(){

        Spartan sp = new Spartan();

        sp.setGender("Female");
        sp.setName("Alisa");
        sp.setPhone(123123123123L);

        File jsonFile = new File("spartan.json");

        Response response=
        given()
                .contentType(ContentType.JSON).log().all()
                .body(jsonFile)

                .when()
                .post("/spartans").prettyPeek();

       id= response.jsonPath().getInt("data.id");
        System.out.println("ID is set -->" +id);
    }


    @Test
    public void deleteSpartan(){

        System.out.println("Current id is --> "+id);

        when().delete("spartans/{id}",id).prettyPeek();
    }

}
