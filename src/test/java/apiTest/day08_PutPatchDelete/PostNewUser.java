package apiTest.day08_PutPatchDelete;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;

import org.testng.annotations.BeforeClass;

import static io.restassured.RestAssured.baseURI;

public class PostNewUser {

    @BeforeClass
    public void beforeClass() {
        baseURI= "https://www.krafttechexlab.com/sw/api/v1";
    }

    @Test
    public void newUser(){

        String body="{\n" +
                "  \"name\": \"M.Ali Mamali\",\n" +
                "  \"email\": \"malimali@krafttechexlab.com\",\n" +
                "  \"password\": \"12345678\",\n" +
                "  \"about\": \"Not Me\",\n" +
                "  \"terms\": \"20\"\n" +
                "}";

        Response response=given().accept(ContentType.JSON)
                .body(body)
                .when().log().all()
                .post("/allusers/register").prettyPeek();


        String token=response.path("token");

        System.out.println("token = " + token);


    }
    //eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJhdXQiOiJhRm0iLCJsaW5rIjoia3JhZnR0ZWNoZXhsYWIuY29tIiwidXNlcmlkIjoiMzU5Iiwic3RhcnQiOjE2NzUxMDk4NzgsImVuZHMiOjE2NzU3MTQ2Nzh9.6aFUcJlsXhOKbjPi8dB8aq7aXrcqB3afzBgtp-Mn7U1Dv6Zg-Qn4Th9mvt0nUIPwDoDRqjnpF4Jf96K_P_0D6g
//id=359
}
