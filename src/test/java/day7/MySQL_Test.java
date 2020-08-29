package day7;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utilities.DB_Utility;

public class MySQL_Test {


    @BeforeAll
    public static void initDB(){
        DB_Utility.createConnection("library1");
    }


    @AfterAll
    public static void destroy(){
        DB_Utility.destroy();
    }

    @DisplayName("Connection check")
    @Test
    public void test(){

        DB_Utility.runQuery("Select * from books");
        DB_Utility.displayAllData();
    }

}
