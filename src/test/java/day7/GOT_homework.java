package day7;

import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.hamcrest.Matchers.*;
import  org.hamcrest.MatcherAssert;
import java.util.List;

import static io.restassured.RestAssured.*;

public class GOT_homework {


@DisplayName("Getting all the members of House Stark")
    @Test
    public void testingGOT(){
    Response response=
    given()
            .baseUri("https://api.got.show")
            .basePath("/api/book").
    when()
    .get("/characters");

    List<String> houseCardList =
            response.jsonPath().getList("findAll{it.house== 'House Stark'}.name");

    System.out.println(houseCardList.size());

    MatcherAssert.assertThat(houseCardList, hasSize(76));
    MatcherAssert.assertThat(houseCardList,hasItem("Arya Stark"));
    MatcherAssert.assertThat(houseCardList, hasItems("Arya Stark","Eddard Stark","Lyanna Stark"));

}

}
