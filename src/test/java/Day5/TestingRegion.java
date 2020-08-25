package Day5;

import POJO.Region;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class TestingRegion {


    @BeforeAll
    public static void setUp(){
        RestAssured.baseURI="http://52.3.242.24";
        RestAssured.port=1000;
        RestAssured.basePath="/ords/hr";
    }


    @Test
    public void testRegion(){
        Response response=
        RestAssured.given()
                .log().all()
                .accept(ContentType.JSON).

        when()
                .get("/regions/{region_id}",1).prettyPeek();


        Region region = response.as(Region.class);





    }
}
