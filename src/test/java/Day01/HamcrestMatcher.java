package Day01;

import org.apache.groovy.json.FastStringService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class HamcrestMatcher {

    //Hamcrest library is a assertion library
    // to make tests more human readable

    @Test
    public void test1() {
        int num1 = 5;
        int num2 = 4;
        //couple assertion methods
        //is ( some value )
        //equalTo ( some value )
        //is ( equalTo (some value) )
        assertThat(num1 + num2, is(9));
        assertThat(num1 + num2, equalTo(9));
        assertThat(num1 + num2, is(equalTo(9)));

        //not
        assertThat(num1 + num2, not(10));
        assertThat(num1 + num2, is(not(10)));
        assertThat(num2 + num1, is(not(equalTo(10))));

        String name = "Firuz";
        String lastName = "Khalimov";
        assertThat(name + lastName, is("FiruzKhalimov"));
        assertThat(name + lastName, equalTo(name.concat(lastName)));
        assertThat(name, not(lastName));

        // how to check in caseInsesetive matter
        //String matchers

        assertThat(name, equalToIgnoringCase("firuz"));
        //how to ignore all white spaces
        assertThat("Firuz ", equalToCompressingWhiteSpace("Firuz"));
    }

    @DisplayName("Support for collection")
    @Test
    public void test2() {
        List<Integer> numlist = Arrays.asList(11, 22, 33, 44, 55);
        assertThat(numlist, hasSize(5));
        assertThat(numlist.size(), is(5));
        assertThat(numlist, hasItem(11));
        assertThat(numlist, hasItems(11, 22));
    }

    @DisplayName("Using Hamcrest for assertion")
    @Test
    public void testingWithMatcher() {
        when().
                get("http://54.174.216.245:8000/api/hello").   // sending a request to this url
                prettyPeek().
                then().               // assertions start here
                statusCode(is(200)).        // asserting status code is 200
                header("Content-Length", equalTo("17")).  // asserting header is "17"
                header("Content-Type", is("text/plain;charset=UTF-8")).
                body(is("Hello from Sparta"));
    }

    @DisplayName("Using Hamcrest matcher for Spartan")
    @Test
    public void spartan(){
        when().
        get("http://54.174.216.245:8000/api/spartans/763").
                prettyPeek().
                then().
                statusCode(is(200));
        System.out.println("The work is done");
    }


}
