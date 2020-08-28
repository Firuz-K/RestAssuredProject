package day5;

import POJO.Departments;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class DepartementsSerialization {


    @BeforeAll
    public static void setUp(){
        RestAssured.baseURI="http://52.3.242.24";
        RestAssured.port=1000;
        RestAssured.basePath="/ords/hr";
    }


    @Test
    public void gettingAllDeps(){

        Response response=
        RestAssured.given()
                .accept(ContentType.JSON).
                when()
                .get("/departments/{department_id}",250).prettyPeek();

        JsonPath jp = response.jsonPath();

        Departments dp= response.as(Departments.class);

       // List<String> list1 = jp.getList("items");

      //  String list1 = jp.getString("items[0].department_name");

        //List<String> list1 = jp.getList("items.department_name");

        //List<Departments> list1 = jp.getList("items",Departments.class);

        System.out.println("==================================");
        System.out.println(dp);
    }

}
