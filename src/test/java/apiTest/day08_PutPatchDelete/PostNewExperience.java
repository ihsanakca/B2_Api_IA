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


import static io.restassured.RestAssured.baseURI;

public class PostNewExperience {
    @BeforeClass
    public void beforeClass() {
        baseURI= "https://www.krafttechexlab.com/sw/api/v1";
    }

    @Test
    public void newExperience(){
        String body="{\n" +
                "  \"job\": \"Python Developer\",\n" +
                "  \"company\": \"Rumba Corps\",\n" +
                "  \"location\": \"Swiss\",\n" +
                "  \"fromdate\": \"2020-10-01\",\n" +
                "  \"todate\": \"2022-01-01\",\n" +
                "  \"current\": \"false\",\n" +
                "  \"description\": \"Great Job\"\n" +
                "}";

        Response response=given().accept(ContentType.JSON)
                .header("token","eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJhdXQiOiJhRm0iLCJsaW5rIjoia3JhZnR0ZWNoZXhsYWIuY29tIiwidXNlcmlkIjoiMzU5Iiwic3RhcnQiOjE2NzUxMDk4NzgsImVuZHMiOjE2NzU3MTQ2Nzh9.6aFUcJlsXhOKbjPi8dB8aq7aXrcqB3afzBgtp-Mn7U1Dv6Zg-Qn4Th9mvt0nUIPwDoDRqjnpF4Jf96K_P_0D6g")
                .body(body)
                .when().log().all()
                .post("experience/add").prettyPeek();

    }
    //experience id=270,271,272
}
