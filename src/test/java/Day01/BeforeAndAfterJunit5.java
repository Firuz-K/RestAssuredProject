package Day01;

import org.junit.jupiter.api.*;

public class BeforeAndAfterJunit5 {

    @BeforeAll // before all will run only once before entire test
    //same idea as @BeforeCLass we learned previously
    // these are junit5 specific annotations
    public static void setup(){
        System.out.println("This runs before all");
    }

    @BeforeEach
    public void beforeEachTest(){
        System.out.println("Running before each test");
    }

    @Test
    public void test1(){
        System.out.println("Test1 is running");
    }
    @Disabled
    @Test
    public void test2(){
        System.out.println("Test2 is running");
    }


    @AfterEach
    public void afterEachTest(){
        System.out.println("This runs after each test");
    }
    @AfterAll
    public static void cleanUp(){
        System.out.println("This is AfterAll");
    }

}
