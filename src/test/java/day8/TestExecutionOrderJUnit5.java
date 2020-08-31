package day8;

import org.junit.jupiter.api.*;

//This annotation indicates to run the test in the order we give
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestExecutionOrderJUnit5 {


    @Order(3)
    @Test
    public void testB(){

    }

    @Order(4)
    @Test
    public void testA(){

    }

    @Order(1)
    @Test
    public void testZ(){

    }

    @Order(2)
    @Test
    public void testK(){

    }


}
