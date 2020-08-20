package Day02;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static io.restassured.matcher.RestAssuredMatchers.*;

public class Practice1 {

    @DisplayName("Get All Characters Simple Test")
    @Test
    public void testBreakingBad() {
        String url = "https://www.breakingbadapi.com/api/characters";
        when()

                .get(url)
                .


                then()
                .statusCode(is(200))   // checking the status code
                .header("Content-Type", "application/json; charset=utf-8");



    }


}
