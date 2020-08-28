package day6;

import  static io.restassured.RestAssured.*;


import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

public class HR_ORDS_GroovyMagic {

    @BeforeAll
    public static void setUp(){
        baseURI="http://52.3.242.24";
        port=1000;
        basePath="/ords/hr";
    }

    @Test
    public void testEmployees(){
        Response response = get("/employees").prettyPeek();

        List<Integer> listOfId = response.jsonPath().getList("items.employee_id");



        //Using groovy to print out elements from 01 till last one
       // System.out.println("Give me first till the fifth "+response.jsonPath().getList("items[0..4].employee_id"));

      //  System.out.println("response.jsonPath().getList(\"items[10..15].last-name\") = " + response.jsonPath().getList("items[10..15].last_name.toUpperCase"));

      //  System.out.println("response.jsonPath().getString(\"\\\"items[0].last_name.toUpperCase\") = " + response.jsonPath().getString("items[0].last_name.toUpperCase"));

        System.out.println("response.jsonPath().getString(\"items.find{it.employee_id == 105}.last_name\") = " + response.jsonPath().getString("items.find{it.employee_id == 105}.last_name"));

        System.out.println("response.jsonPath().getString(\"items.find{it.first_name == 'Sigal'}.phone_number\") = " + response.jsonPath().getString("items.find{it.first_name == 'Sigal'}.phone_number"));
        int salary = response.jsonPath().getInt("items.find{it.last_name == 'King'}.salary");
       List<String> richPeople= response.jsonPath().getList("items.findAll{it.salary > 15000}.first_name");
        System.out.println("Rich Peopls --> "+richPeople);
        System.out.println("Salary is = "+salary);


    }
}
