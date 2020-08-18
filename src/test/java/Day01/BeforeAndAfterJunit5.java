package Day01;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class BeforeAndAfterJunit5 {

    @BeforeAll // before all will run only once before entire test
    //same idea as @BeforeCLass we learned previously
    // these are junit5 specific annotations
    public static void setup(){
        System.out.println("This runs before all");
    }
    @Test
    public void test1(){
        System.out.println("Test is running");
    }

    @AfterAll
    public static void cleanUp(){
        System.out.println("This is cleanup");
    }

}
