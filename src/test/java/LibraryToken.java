import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import utilities.ConfigurationReader;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class LibraryToken {

   private static String libraryToken;




    @BeforeAll
    public static void setUp(){
        String username=ConfigurationReader.getProperty("library1.librarian_username");
        String password = ConfigurationReader.getProperty("library1.librarian_password");
        baseURI="http://library2.cybertekschool.com";
       basePath="/rest/v1";
        libraryToken =gettingToken(username,password);
    }

@Test
public void test(){
        given()
                .contentType(ContentType.URLENC)
                .header("x-library-token",libraryToken).log().all()

                .when()
                .get("/dashboard_stats").prettyPeek()

                .then()
                .statusCode(is(200))
                .body("book_count", equalTo("1671"))
                .body("borrowed_books",is(equalTo("108")))
                .body("users",is("764"))
                .header("Content-Type",is("application/json; charset=utf-8"));
}


    public static String gettingToken(String email, String password){
        Response response=
        given()
                //.body(ContentType.URLENC)
                .formParam("email",email)
                .formParam("password",password)

                .when()
                .post("/login");

      String token= response.jsonPath().getString("token");
       return token;
    }



}
