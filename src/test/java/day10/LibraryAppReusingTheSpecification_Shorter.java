package day10;

import static io.restassured.RestAssured.*;

import POJO.Category;
import POJO.User;
import io.restassured.RestAssured;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utilities.ConfigurationReader;
import utilities.LibraryUtil;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.expect;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class LibraryAppReusingTheSpecification_Shorter {


    @BeforeAll
    public static void setUp() {
        RestAssured.baseURI = ConfigurationReader.getProperty("library1.base_url");
        RestAssured.basePath = "/rest/v1";


        String token = LibraryUtil.loginAndGetToken(ConfigurationReader.getProperty("library1.librarian_username"),
                ConfigurationReader.getProperty("library1.librarian_password"));

        RestAssured.requestSpecification = given()
                .accept(ContentType.JSON)
                .log().all()
                .header("x-library-token", token);

        RestAssured.responseSpecification = expect().statusCode(is(200))
                .contentType(ContentType.JSON)
                .logDetail(LogDetail.ALL);
    }


    @Test
    public void testLibrary() {

        Response response =
                when()
                        .get("/get_book_categories");


        JsonPath jp = response.jsonPath();

        List<Category> map =jp.getList("",Category.class);
        System.out.println(map);

        List<Map<String, String>> listOfMap = jp.getList("");
        System.out.println(listOfMap);


    }

    @DisplayName("Getting all users")
    @Test
    public void testGetAllUser() {
        Response response =
                when()
                        .get("/get_all_users");

        JsonPath jp = response.jsonPath();

        List<User> listOfUsers=jp.getList("", User.class);
        System.out.println(listOfUsers);


    }


    @DisplayName("Getting dashboard stats")
    @Test
    public void testGetDashboardStatus() {

        Response response =
                when()
                        .get("/dashboard_stats").prettyPeek();

        JsonPath jp = response.jsonPath();

        Map<String, String> map = new LinkedHashMap<>();
        map = response.jsonPath().getMap("");


/*
       String bookCount= jp.getString("book_count");
       String borrowed_books=jp.getString("borrowed_books");
       String users = jp.getString("users");

         map.put("book_count ", bookCount);
         map.put("borrowed_books ",borrowed_books);
         map.put("users ",users);

 */

        System.out.println(map);


    }


}
