package erenHocaAPI;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class day7_PatchMethod {
    //PATCH
    //TASK

    /*
    REGISTER A NEW USER
    baseUrl = https://www.krafttechexlab.com/sw/api/v1
    endpoint = /allusers/register
    Given accept type and Content type is JSON
    And request json body is:
       {
      "name": "xxxxxx61",
      "email": "xxxxxx61@xxxxxx.com",
      "password": "xxxxxx61",
       }
    /

    /
    LOGIN WITH THE SAME USER AND GET THE TOKEN
    Base Url = https://www.krafttechexlab.com/sw/api/v1
    End Point = /allusers/login
    email = xxxxxx61@xxxxxx.com
    password = xxxxxx61
    Given request body should have email and password as multipart/form-data
    Get the token has been got by response body and assign this token to a global String
    /

    /
    UPDATE SAME USER
    PATCH METHOD
    baseUrl = https://www.krafttechexlab.com/sw/api/v1
    endpoint = /user/update
    TOKEN SHOULD BE PROVIDED AS A HEADER TO BE ABLE TO SEND A REQUEST SUCCESSFULLY
    THE BODY THAT IS GOING TO BE UPDATED SHOULD BE PROVIDED AS JSON
    name should be changed as ihsan
    */

    @Test
    public void test1(){

        Map<String,Object> bodyMap=new HashMap<>();
        bodyMap.put("name","xxxxxx61");
        bodyMap.put("email","xxxxxx61@xxxxxx.com");
        bodyMap.put("password","xxxxxx61");

        Response response = RestAssured
                .given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(bodyMap)
                .when().log().all()
                .post("https://www.krafttechexlab.com/sw/api/v1/allusers/register");

        System.out.println("response.statusCode() = " + response.statusCode());
        System.out.println("response.path(\"id\") = " + response.path("id"));
        System.out.println("Register ends here");
        System.out.println("Lets login now");

        String token;
        Response response1 = RestAssured
                .given()
                .contentType("multipart/form-data")
                .multiPart("email", "xxxxxx61@xxxxxx.com")
                .multiPart("password", "xxxxxx61")
                .when()
                .post("https://www.krafttechexlab.com/sw/api/v1/allusers/login");

        token=response1.path("token");
        System.out.println("response1.statusCode() = " + response1.statusCode());
        System.out.println("login ends here");
        System.out.println("lets move to patch method");

        //update name xxxxxx61 to ihsan

        Map<String,Object> patchBodyMap=new HashMap<>();
        patchBodyMap.put("name","ihsan");

        Response response2 = RestAssured
                .given()
                .header("token", token)
                .body(patchBodyMap)
                .when()
                .patch("https://www.krafttechexlab.com/sw/api/v1/user/update");

        System.out.println("response2.statusCode() = " + response2.statusCode());

    }
}
