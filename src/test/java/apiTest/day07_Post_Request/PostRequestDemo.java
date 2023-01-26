package apiTest.day07_Post_Request;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

public class PostRequestDemo {

    @BeforeClass
    public void beforeClass() {
        baseURI = "https://www.krafttechexlab.com/sw/api/v1";
    }

    @Test
    public void postNewUser() {
        String jsonBody = "{\n" +
                "  \"name\": \"Selim Gezmez61\",\n" +
                "  \"email\": \"selo61@gmail.com\",\n" +
                "  \"password\": \"12345678\",\n" +
                "  \"about\": \"From Siirt\",\n" +
                "  \"terms\": \"3\"\n" +
                "}";

        Response response = given().accept(ContentType.JSON)       //hey api send me body as json format
                .and()
                .contentType(ContentType.JSON)   //hey api i am sending json body
                .and()
                .body(jsonBody)
                .when()
                .post("allusers/register");

        assertEquals(response.statusCode(), 200);
        response.prettyPrint();

        assertTrue(response.body().asString().contains("token"));
    }

    @Test
    public void postNewUser2() {
        Map<String, Object> requestMap = new HashMap<>();

        requestMap.put("name", "selim gezmez62");
        requestMap.put("email", "selimgezmez62@gmail.com");
        requestMap.put("password", "12345678");
        requestMap.put("about", "nothing");
        requestMap.put("terms", "33");

        Response response = given().accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .and()
                .body(requestMap)  //serialization
                .when()
                .post("allusers/register");

        assertEquals(response.statusCode(), 200);
        response.prettyPrint();

        assertTrue(response.body().asString().contains("token"));
    }

    @Test
    public void postNewUser3() {
        NewUserInfo newUserInfo = new NewUserInfo();

        newUserInfo.setName("Selim Gezmez64");
        newUserInfo.setEmail("sgezmez64@gmail.com");
        newUserInfo.setPassword("12345678");
        newUserInfo.setAbout("nobody knows");
        newUserInfo.setTerms("33");

        Response response = given().accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .and()
                .body(newUserInfo)  //serialization
                .when()
                .post("allusers/register");

        assertEquals(response.statusCode(), 200);
        response.prettyPrint();

        assertTrue(response.body().asString().contains("token"));

    }

    @Test
    public void postNewUser4(){
        NewUserInfo newUserInfo=new NewUserInfo("Selim Gezmez65","sgezmez65@gmail.com",
                "12345678","who knows","33");

        Response response = given().accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .and()
                .body(newUserInfo)  //serialization
                .when()
                .post("allusers/register");

        assertEquals(response.statusCode(), 200);
        response.prettyPrint();

        assertTrue(response.body().asString().contains("token"));

    }
}
