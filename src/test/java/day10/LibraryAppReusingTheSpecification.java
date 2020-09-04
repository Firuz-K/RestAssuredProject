package day10;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import io.restassured.RestAssured;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utilities.ConfigurationReader;
import utilities.LibraryUtil;

public class LibraryAppReusingTheSpecification {

    static RequestSpecification reqSpec;
    static ResponseSpecification resSpec;

    @BeforeAll
    public static void setUp(){
        RestAssured.baseURI= ConfigurationReader.getProperty("library1.base_url");
        RestAssured.basePath="/rest/v1";

        String token = LibraryUtil.loginAndGetToken(ConfigurationReader.getProperty("library1.librarian_username"),
                ConfigurationReader.getProperty("library1.librarian_password"));

        reqSpec= given()
                .accept(ContentType.JSON)
                .header("x-library-token",token);
        resSpec= expect().statusCode(is(200))
                .contentType(ContentType.JSON)
                .logDetail(LogDetail.ALL);
    }


    @Test
    public void testLibrary(){

        given()
                .spec(reqSpec).

                when()
                .get("/get_book_categories").

                then()
                .spec(resSpec);
    }

    @DisplayName("Getting all users")
    @Test
    public void testGetAllUser(){

        given().
                spec(reqSpec).

                when()
                .get("/get_all_users").

                then()
                .spec(resSpec);
    }


    @DisplayName("Getting dashboard stats")
    @Test
    public void testGetDashboardStatus(){
        given()
                .spec(reqSpec)

                .when()
                .get("/dashboard_stats")
                .then()
                .spec(resSpec);
    }




}
