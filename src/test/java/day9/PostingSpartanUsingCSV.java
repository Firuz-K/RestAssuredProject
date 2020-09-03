package day9;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import utilities.ConfigurationReader;
import utilities.DB_Utility;


import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.*;

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
    public void testAddSpartan(String csvName, String csvGender, long csvPhone){

        //providing teh body using POJO
       //Spartan spartan = new Spartan(csvName,csvGender,csvPhone);

        Map<String,Object> map = new HashMap<>();
        map.put("name",csvName);
        map.put("gender",csvGender);
        map.put("phone",csvPhone);

        RestAssured.given().

                contentType(ContentType.JSON)
                .body(map)
                .when().post("/spartans").prettyPeek().

                then()
                .statusCode(201)
                .body("success",is("A Spartan is Born!"))
                .body("data.id",notNullValue())
                .body("data.name",equalTo(csvName));

    }



}
