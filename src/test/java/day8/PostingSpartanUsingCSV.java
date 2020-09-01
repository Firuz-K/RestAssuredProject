package day8;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import utilities.ConfigurationReader;
import utilities.DB_Utility;

public class PostingSpartanUsingCSV {


    @BeforeAll
    public static void setUp() {
        RestAssured.baseURI = "http://52.3.242.24";
        RestAssured.port = 8000;
        RestAssured.basePath = "/api";
        DB_Utility.createConnection(ConfigurationReader.getProperty("spartan1.database.url"),
                ConfigurationReader.getProperty("spartan1.database.username"),
                ConfigurationReader.getProperty("spartan1.database.password"));
    }

    @ParameterizedTest()
    @CsvFileSource(resources = "/Spartan.csv",numLinesToSkip = 1)
    public void testAddSpartan(String name, String gender, String phone){

        RestAssured.given().

                contentType(ContentType.JSON)
                .body("{\n" +
                        "    \"name\":"+ name+"\n" +
                        "    \"gender\":"+gender+"\n" +
                        "    \"phone\":" +phone+"\n" +
                        "  }")
                .when().post("/spartans").prettyPeek();






    }

}
