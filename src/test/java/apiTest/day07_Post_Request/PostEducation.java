package apiTest.day07_Post_Request;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static org.hamcrest.Matchers.*;

public class PostEducation {

    @BeforeClass
    public void beforeClass() {
        baseURI = "https://www.krafttechexlab.com/sw/api/v1";
    }

    @Test
    public void postNewUser(){
        NewUserInfo newUserInfo=new NewUserInfo("Selim Gezmez659","sgezmez659@gmail.com",
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

        String token=response.path("token");

        String educationBody="{\n" +
                "  \"school\": \"Ucanevler\",\n" +
                "  \"degree\": \"Second\",\n" +
                "  \"study\": \"Orta\",\n" +
                "  \"fromdate\": \"2020-01-01\",\n" +
                "  \"todate\": \"2022-01-01\",\n" +
                "  \"current\": \"false\",\n" +
                "  \"description\": \"Bagcilar\"\n" +
                "}";

        response=given().accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .and()
                .body(educationBody)
                .and()
                .queryParam("token",token)
                .when()
                .post("education/add");

        response.prettyPrint();
        assertEquals(response.statusCode(),200);
    }

    @Test
    public void postNewUserAndVerify(){
        String name="Selim Gezmez616122";
        String email="sg616122@gmail.com";
        String password="12345678";
        String about="who is the sg";
        String terms="33";

        Map<String,Object> requestMap=new HashMap<>();

        requestMap.put("name", name);
        requestMap.put("email", email);
        requestMap.put("password", password);
        requestMap.put("about", about);
        requestMap.put("terms", terms);

        Response response = given().accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .and()
                .body(requestMap)  //serialization
                .when()
                .post("allusers/register");

        String token=response.path("token");
        //String userID=response.path("id").toString();

        Map<String ,Object> educationBody=new HashMap<>();

        educationBody.put("school","naci eksi");
        educationBody.put("degree","first one");
        educationBody.put("study","tester");
        educationBody.put("fromdate","2020-01-01");
        educationBody.put("todate","2022-01-01");
        educationBody.put("current","false");
        educationBody.put("description","bagcilarrrr");

        response=given().accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .and()
                .body(educationBody)
                .and()
                .queryParam("token",token)
                .when()
                .post("education/add");

        response.prettyPrint();
        assertEquals(response.statusCode(),200);

        //verify body

       int id=response.path("id");
        //System.out.println("id = " + id);

        response=given().accept(ContentType.JSON)
                .and()
                .queryParam("token",token)
                .and()
                .pathParam("id",id)
                .when()
                .get("/education/getbyid/{id}");

        response.prettyPrint();

        //verify with path
        //System.out.println("userID = " + userID);
        assertEquals(response.path("school"),"naci eksi");
        assertEquals(response.path("study"),"tester");
        //assertEquals(response.path("userid"),userID);

        //

        given().accept(ContentType.JSON)
                .and()
                .queryParam("token",token)
                .and()
                .pathParam("id",id)
                .when()
                .get("/education/getbyid/{id}")
                .then()
                .assertThat()
                .body("school",equalTo("naci eksi"),
                        "study",equalTo("tester")).log().all();

    }
}

