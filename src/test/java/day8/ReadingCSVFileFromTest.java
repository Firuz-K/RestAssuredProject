package day8;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public class ReadingCSVFileFromTest {



    @ParameterizedTest
    @CsvFileSource(resources = "/file.csv",numLinesToSkip = 1)
    public void simpleRead(int num1, int num2){

        System.out.println("num1 = " + num1);
        System.out.println("num2 = " + num2);

    }

    @ParameterizedTest(name = "Row{index}|First Col{0} | SecondCol {1} |ThirdCol {2}")
   // @ParameterizedTest(name="B18 Learning Math {index}")
    @CsvFileSource(resources = "/numbers.csv",numLinesToSkip = 1)
    public void testAddition(int num1, int num2, int additionResult){

        assertEquals(num1+num2,additionResult);


    }

}
