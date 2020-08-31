package day8;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import utilities.ConfigurationReader;

import static org.hamcrest.Matchers.*;

public class LibraryApp_DataDriven {


    @BeforeAll
    public static void srtUp(){
        RestAssured.baseURI= ConfigurationReader.getProperty("library1.base_url");
        RestAssured.basePath ="/rest/v1";
    }


    @ParameterizedTest(name = "iteration {index} | username:{0} , password:{1}")
    @CsvFileSource(resources = "/credentials.csv",numLinesToSkip = 1)
    public void loginWithCredentials(String user, String pass){

        RestAssured.given()
                .contentType(ContentType.URLENC)
                .formParam("email", user)
                .formParam("password",pass).log().all()

                .when()
                .post("/login").

                then()
                .statusCode(200)
                .body("token", notNullValue());

    }

    @AfterAll
    public static void destroy(){
        RestAssured.reset();
    }
}
