package Day01;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Sample_Test {

    @Test
    public void calculatorTest(){
        System.out.println("Hello World");
        Assertions.assertEquals(4+5,9);

        assertEquals(10,5+5,"Hey wrong result");
    }

    @DisplayName("I am testing the name")
    @Test
    public void nameTest(){
        String name ="Firuz";
        String lastName="Khalimov";
        assertEquals(name+" "+lastName,"Firuz Khalimov");

    }



}
