import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SpartanXML {


    @BeforeAll
    public static void setUp() {

        baseURI = "http://52.3.242.24";
        port = 8000;
        basePath = "/api";

    }


    @Test
    public void test() {

        given()

                .accept(ContentType.XML)
                .when()

                .get("/spartans").prettyPeek()

                .then()

                .statusCode(is(200))
                .contentType(ContentType.XML)
                .body("List.item[0].id", is("195"))
                .body("List.item[0].name", is("Jackson"))
                .body("List.item[0].gender", is("Male"))
                .body("List.item[0].phone", is("123412341234"))
        ;

    }


    @Test
    public void test2() {

        Response response =
                given()
                        .accept(ContentType.XML)

                        .when()

                        .get("/spartans");


        XmlPath xmlPath = response.xmlPath();

        List<String> listOfStrings = xmlPath.getList("List.item.id");
        System.out.println("listOfStrings = " + listOfStrings);

        int firstID = xmlPath.getInt("List.item[1].id");
        System.out.println("firstID = " + firstID);

        String name = xmlPath.getString("List.item[0].name");
        System.out.println("name = " + name);

        String gender = xmlPath.getString("List.item[0].gender");
        System.out.println("gender = " + gender);
    }


    @Test
    public void test3() {
        Response response =
                given()
                        .accept(ContentType.XML)
                        .log().all().

                        when().

                        get("/spartans");

        XmlPath xmlPath = response.xmlPath();

        List<String> listOfNames = xmlPath.getList("List.item.name");

        System.out.println("listOfNames = " + listOfNames);

        Map<String, String> map = new LinkedHashMap<>();

        for(int i =0; i<listOfNames.size();i++){
            map.put("name",listOfNames.get(i));

        }
        System.out.println("map = " + map);



    }
}
