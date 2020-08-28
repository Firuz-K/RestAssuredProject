package day01;

import  io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Practice1 {

    // we are using spartan IP that does not require password
    String url = "http://23.23.75.140:8000/api/hello";
    Response response;

    @Test
    public void test(){
        response = get(url);
        System.out.println("response.statusCode() = " + response.statusCode());
        System.out.println("response.getStatusLine() = " + response.getStatusLine());
        System.out.println("response.header(\"Date\") = " + response.header("Date"));
        System.out.println("response.header(\"Content-Type\") = " + response.header("Content-Type"));
        System.out.println("response.header(\"Content-Length\") = " + response.header("Content-Length"));

        assertEquals(response.getStatusCode(),200);
        assertEquals(response.header("Content-Type"),"text/plain;charset=UTF-8");
        assertEquals("HTTP/1.1 200 ",response.getStatusLine());
    }

    @DisplayName("Testing/Hello")
    @Test
    public void testHello(){

        response = get(url);
        System.out.println("response.getStatusCode() = " + response.getStatusCode());
        assertEquals(200,response.getStatusCode());
        assertEquals("17",response.header("Content-Length"));

    }

    @Test
    public void testingBody(){
        response = get(url);
        String responseStr = response.asString();
        System.out.println(responseStr);
        System.out.println(response.body().asString());
        assertEquals(responseStr,"Hello from Sparta");
        assertEquals(17,responseStr.length());
    }

    @DisplayName("Printing response body using method")
    @Test
    public void printingBody(){
        response = get(url);
        //String body= response.prettyPrint();
        System.out.println("============================================");
        //response.prettyPeek();
        System.out.println("==============================================");

        //PrettyPeak will print entire response
        int statusCode = response.prettyPeek().statusCode();
        System.out.println(statusCode);

    }
}
