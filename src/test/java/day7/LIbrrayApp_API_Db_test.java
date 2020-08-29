package day7;

import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import utilities.ConfigurationReader;
import utilities.DB_Utility;
import utilities.LibraryUtil;

import static utilities.LibraryUtil.loginAndGetToken;

public class LIbrrayApp_API_Db_test {

private static String libraryToken;

    @BeforeAll
    public static void init(){
        RestAssured.baseURI= ConfigurationReader.getProperty("library1.base_url");
        RestAssured.basePath="/rest/v1";
        libraryToken = loginAndGetToken(ConfigurationReader.getProperty("library1.librarian_username"),
                ConfigurationReader.getProperty("library1.librarian_password"));
        DB_Utility.createConnection("library1");
    }

    @AfterAll
    public static void destroy(){
        DB_Utility.destroy();
        RestAssured.reset(); // this is for resetting all the values we set for RestAssured itself
    }
    @Test
    public void test(){
        System.out.println("LibraryToken = "+libraryToken);
    }


}
