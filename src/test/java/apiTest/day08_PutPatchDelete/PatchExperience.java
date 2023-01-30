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

public class PatchExperience {
    @BeforeClass
    public void beforeClass() {
        baseURI = "https://www.krafttechexlab.com/sw/api/v1";
    }

    @Test
    public void updateExperienceWithPatch(){
        String body="{\n" +
                "  \"job\": \"Business Analyst_23\",\n" +
                "  \"location\": \"Bhutan\",\n" +
                "  \"description\": \"beter one\"\n" +
                "}";

        Response response=given().accept(ContentType.JSON)
                .pathParam("id",272)
                .header("token","eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJhdXQiOiJhRm0iLCJsaW5rIjoia3JhZnR0ZWNoZXhsYWIuY29tIiwidXNlcmlkIjoiMzU5Iiwic3RhcnQiOjE2NzUxMDk4NzgsImVuZHMiOjE2NzU3MTQ2Nzh9.6aFUcJlsXhOKbjPi8dB8aq7aXrcqB3afzBgtp-Mn7U1Dv6Zg-Qn4Th9mvt0nUIPwDoDRqjnpF4Jf96K_P_0D6g")
                .body(body)
                .when().log().all()
                .patch("/experience/updatepatch/{id}");

        Assert.assertEquals(response.statusCode(),200);

        System.out.println("response.prettyPrint() = " + response.prettyPrint());
    }
}
