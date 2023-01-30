package apiTest.day08_PutPatchDelete;

import org.testng.annotations.BeforeClass;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;

import org.testng.annotations.BeforeClass;

public class PutExperience {
    @BeforeClass
    public void beforeClass() {
        baseURI = "https://www.krafttechexlab.com/sw/api/v1";
    }

    @Test
    public void updateExperienceWithPut() {
        String body = "{\n" +
                "  \"job\": \"Junior SDET\",\n" +
                "  \"company\": \"Texas Ins\",\n" +
                "  \"location\": \"USA\",\n" +
                "  \"fromdate\": \"2020-11-22\",\n" +
                "  \"todate\": \"2021-02-03\",\n" +
                "  \"current\": \"false\",\n" +
                "  \"description\": \"Second One\"\n" +
                "}";

        Response response = given().accept(ContentType.JSON)
                .queryParam("id", 270)
                .header("token", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJhdXQiOiJhRm0iLCJsaW5rIjoia3JhZnR0ZWNoZXhsYWIuY29tIiwidXNlcmlkIjoiMzU5Iiwic3RhcnQiOjE2NzUxMDk4NzgsImVuZHMiOjE2NzU3MTQ2Nzh9.6aFUcJlsXhOKbjPi8dB8aq7aXrcqB3afzBgtp-Mn7U1Dv6Zg-Qn4Th9mvt0nUIPwDoDRqjnpF4Jf96K_P_0D6g")
                .body(body)
                .when().log().all()
                .put("/experience/updateput").prettyPeek();
    }

    @Test
    public void updateExperienceWithPut2() {
        String body = "{\n" +
                "  \"job\": \"Junior  QA SDET\",\n" +
                "  \"company\": \"Silk Valley Ins\",\n" +
                "  \"location\": \"Cal\",\n" +
                "  \"fromdate\": \"2020-11-22\",\n" +
                "  \"todate\": \"2021-02-03\",\n" +
                "  \"current\": \"false\",\n" +
                "  \"description\": \"Quick One\"\n" +
                "}";

        Response response = given().accept(ContentType.JSON)
                .queryParam("id", 271)
                .header("token", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJhdXQiOiJhRm0iLCJsaW5rIjoia3JhZnR0ZWNoZXhsYWIuY29tIiwidXNlcmlkIjoiMzU5Iiwic3RhcnQiOjE2NzUxMDk4NzgsImVuZHMiOjE2NzU3MTQ2Nzh9.6aFUcJlsXhOKbjPi8dB8aq7aXrcqB3afzBgtp-Mn7U1Dv6Zg-Qn4Th9mvt0nUIPwDoDRqjnpF4Jf96K_P_0D6g")
                .body(body)
                .when().log().all()
                .put("/experience/updateput").prettyPeek();
    }


}
