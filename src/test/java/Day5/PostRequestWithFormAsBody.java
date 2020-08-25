package Day5;

import  static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import   io.restassured.response.*;

import static org.hamcrest.Matchers.*;

public class PostRequestWithFormAsBody {

    //  baseURI = "http://library2.cybertekschool.com"
    // basePath ="/rest/v1"
    // we are working on "/login"

    //post body , type x-www-urlencoded-form-data
    //email librarian69@library
    //password KNPXrm3s


    @BeforeAll
    public static void setUp(){
        baseURI="http://library1.cybertekschool.com";
       basePath="/rest/v1";

    }

    @DisplayName("Post login as Tester")
    @Test
    public void loginTestEndPoint(){
        given()
                .log().all()
                .contentType(ContentType.URLENC)// explicitly saying body contentType is
                .formParam("email","librarian69@library")
                .formParam("password","KNPXrm3S").
        when()
                .post("/login").
        then()
                .statusCode(200)
                .log().all()
                .body("token",is(notNullValue()));

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


@Test
    public void test(){
        loginAndGetToken("librarian69@library","KNPXrm3S");
}

}



