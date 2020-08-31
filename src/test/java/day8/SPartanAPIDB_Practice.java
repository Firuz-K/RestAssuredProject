package day8;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.*;
import utilities.ConfigurationReader;
import utilities.DB_Utility;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

public class SPartanAPIDB_Practice {

    /*
    devs just implemented the search endpoint
    and want it to be tested to make sure it's actually returning the correct
    result the correct result form database
     */

    @BeforeAll
    public static void setUp() {
        RestAssured.baseURI = "http://52.3.242.24";
        RestAssured.port = 8000;
        RestAssured.basePath = "/api";
        DB_Utility.createConnection(ConfigurationReader.getProperty("spartan1.database.url"),
                ConfigurationReader.getProperty("spartan1.database.username"),
                ConfigurationReader.getProperty("spartan1.database.password"));
    }


    @Test
    public void testDB() {
       // DB_Utility.runQuery("Select * from spartans");


        // run this query so we can use it for expected result
        String query = "SELECT * FROM SPARTANS     " +
                " WHERE LOWER(gender) = 'female'  " +
                " and LOWER(name) LIKE '%a%' ";
        DB_Utility.runQuery(query);
       // DB_Utility.displayAllData();
        int rowCount=DB_Utility.getRowCount();
       // assertEquals(rowCount,37,"Numbers do not match !!!");
        MatcherAssert.assertThat(rowCount,is(37));

    }

    @Test
    public void test() {
        //make a request to /spartans/search
        // using query parameter gender Female nameContains

        Response response =
                RestAssured.given()
                        .log().all()
                        .queryParam("gender", "Female")
                        .queryParam("nameContains", "a")

                        .when()
                        .get("/spartans/search").prettyPeek();

        int count = response.jsonPath().getInt("numberOfElements");
        System.out.println("Count is --> " + count);

        String query = "SELECT * FROM SPARTANS     " +
                " WHERE LOWER(gender) = 'female'  " +
                " and LOWER(name) LIKE '%a%' ";
        DB_Utility.runQuery(query);
        // DB_Utility.displayAllData();
        int rowCount=DB_Utility.getRowCount();
        assertEquals(count,rowCount,"Numbers do not match !!!");



    }

    @Test
    public void testSearchVerifyAllID() {
        //make a request to /spartans/search
        // using query parameter gender Female nameContains

        Response response =
                RestAssured.given()
                        .log().all()
                        .queryParam("gender", "Female")
                        .queryParam("nameContains", "a")

                        .when()
                        .get("/spartans/search").prettyPeek();

        //We wnat to store the ID list as a list of string
        List<String> listOfIDFromResponse = response.jsonPath().getList("content.id",String.class);
        System.out.println("List of ID is --> " + listOfIDFromResponse);

        String query = "SELECT * FROM SPARTANS     " +
                " WHERE gender = 'Female'  " +
                " and LOWER(name) LIKE '%a%' ";
        DB_Utility.runQuery(query);
        List<String> listofid1 = DB_Utility.getColumnDataAsList(1);

        assertEquals(listOfIDFromResponse.size(),listofid1.size());
        assertEquals(listofid1,listOfIDFromResponse);






    }

    @AfterAll
    public static void destroy() {
        RestAssured.reset();
        DB_Utility.destroy();
    }
}
