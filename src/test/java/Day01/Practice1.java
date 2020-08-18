package Day01;

import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class Practice1 {

    // we are using spartan IP that does not require password
    //http://23.23.75.140:8000/api/hello

    @Test
    public void test(){
        Response response = get("http://23.23.75.140:8000/api/hello");
        System.out.println("response.statusCode() = " + response.statusCode());
        System.out.println("response.getStatusLine() = " + response.getStatusLine());
        System.out.println("response.header(\"Date\") = " + response.header("Date"));
        System.out.println("response.header(\"Content-Type\") = " + response.header("Content-Type"));
        System.out.println("response.header(\"Content-Length\") = " + response.header("Content-Length"));

    }
}
