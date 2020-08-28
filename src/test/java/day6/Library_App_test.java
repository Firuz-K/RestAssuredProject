package day6;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class Library_App_test {

    private static String libraryToken;
    @BeforeAll
    public static void setUp(){
        RestAssured.baseURI="http://library1.cybertekschool.com";
        RestAssured.basePath="/rest/v1";
        libraryToken = loginAndGetToken("librarian69@library","KNPXrm3S");
    }


    @DisplayName("Send a request to /Dashboard-stats")
    @Test
    public void testDashboardWithToken(){

        RestAssured.given()
                .log().all()
                .header("x-library-token",libraryToken)
        .when()
                .get("/dashboard_stats").prettyPeek().
        then()
                .statusCode(is(200))
                .body("book_count", is("1671"))
                .body("borrowed_books",equalTo("108"))
                .body("users", is("759"));

    }

    @DisplayName("testing POST/decode endpoint")
    @Test
    public void test(){
        RestAssured.given()
                .log().all()
                .contentType(ContentType.URLENC)
                .formParam("token",libraryToken).
        when()
                .post("/decode")
        .then()
                .log().all()
                .statusCode(200)
        .body("email",is("librarian20@library"))

        ;
    }

    @DisplayName("Get user by id/{id} endpoint")
    @Test
    public void testSingleUSerData(){

        RestAssured.given()
                .log().all()
                .header("x-library-token",libraryToken)
                .pathParam("id",2080)
         .when()
                .get("/get_user_by_id/{id}")
         .then()
                .statusCode(200).log().all();


    }


    public static String loginAndGetToken(String email, String password){

        Response response=
                given()
                        .log().all()
                        .formParam("email",email)
                        .formParam("password",password).
                        when()
                        .post("/login");

        JsonPath jp = response.jsonPath();
        String token = jp.getString("token");
        System.out.println(token);
        return token;

    }





}
