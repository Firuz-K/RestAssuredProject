package day10;

import  static io.restassured.RestAssured.*;

import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utilities.ConfigurationReader;
import utilities.DB_Utility;

import java.sql.Connection;
import java.util.Map;

public class GetTestDataFromSpartanDB {

    @BeforeAll
    public static void setUp(){

        baseURI="http://52.3.242.24";
        basePath="/api";
        port=8000;


        DB_Utility.createConnection("spartan1");



    }


    @DisplayName("Testing GET/Spartan/{id} by getting ID from database")
    @Test
    public void testDataFromDB(){

        String query ="Select * from Spartans order by spartan_id desc";

        DB_Utility.runQuery(query);
        DB_Utility.displayAllData();

        Map<String, String > firstRowMap = DB_Utility.getRowMap(1);
        System.out.println(firstRowMap);




    }

    @AfterAll
    public static void destroy(){
       DB_Utility.destroy();
    }


}
