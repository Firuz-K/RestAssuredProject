package utilities;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class LibraryUtil {

    //we want to keep the method that getToken in here
    // so we don't have to copy paste anymore


    public static String loginAndGetToken(String email, String password) {

        Response response =
                given()
                        .log().all()
                        .formParam("email", email)
                        .formParam("password", password).
                        when()
                        .post("/login");

        JsonPath jp = response.jsonPath();
        String token = jp.getString("token");
        System.out.println(token);
        return token;

    }
}