package erenHocaAPI;

import erenHocaAPI.apiPojoTemplates.PostRegisterClass;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;


public class day7_PostMethod {
    //POST METHOD
    /*
    Along with the others, there are 3 particular ways to provide data into the request body when we use post method:
        1. Assign the JSON body inside a string variable and put it into the body() method
        2. Put data inside a map and provide it into the body() method
        NOTE:body() method converts the data inside the map to JSON automatically. This only happens with POST,PUT andPATCH method.
        3. Put data into an object which is created based on a java custom class and put it into the body() method.
     */

    //TASK
    /*
    baseUrl = https://www.krafttechexlab.com/sw/api/v1
    endpoint = /allusers/register
    Given accept type and Content type is JSON
    And request json body is:
    {
    "name": "xxx",
    "email": "xxx@xxx.com",
    "password": "xxx",
    }
    When user sends POST request
    Then status code 200
    And content type should be application/json
    And json payload/response/body should contain:
    a new generated id that is special for user
    name
    email
    ...
     */

    //First Way
    //String
    @Test
    public void test1(){

        String body=" {\n" +
                "    \"name\": \"xxx61\",\n" +
                "    \"email\": \"xxx61@xxx.com\",\n" +
                "    \"password\": \"xxx61\"\n" +
                "    }";


        Response response = RestAssured
                .given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post("https://www.krafttechexlab.com/sw/api/v1/allusers/register");

        assertEquals(response.statusCode(),200);

        String actualName=response.path("name");

        assertEquals(actualName,"xxx61");

        assertEquals(response.path("email"),"xxx61@xxx.com");

        assertNotNull(response.path("id"));

    }
    //TASK
    /*
    baseUrl = https://www.krafttechexlab.com/sw/api/v1
    endpoint = /allusers/register
    Given accept type and Content type is JSON
    And request json body is:
    {
    "name": "xxx",
    "email": "xxx@xxx.com",
    "password": "xxx",
    }
    When user sends POST request
    Then status code 200
    And content type should be application/json
    And json payload/response/body should contain:
    a new generated id that is special for user
    name
    email
    ...
     */

    //SECOND WAY
    //MAP
    @Test
    public void test2(){

        Map<String,Object> bodyMap=new HashMap<>();
        bodyMap.put("name","xxx610");
        bodyMap.put("email","xxx610@xxx.com");
        bodyMap.put("password","xxx61");

        Response response = RestAssured
                .given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(bodyMap)
                .when().log().all()
                .post("https://www.krafttechexlab.com/sw/api/v1/allusers/register");

        Assert.assertEquals(response.statusCode(),200);

        String actualName=response.path("name");

        Assert.assertEquals(actualName,"xxx610");

        Assert.assertEquals(response.path("email"),"xxx610@xxx.com");

        Assert.assertNotNull(response.path("id"));

        response.prettyPrint();

    }
    //TASK
    /*
    baseUrl = https://www.krafttechexlab.com/sw/api/v1
    endpoint = /allusers/register
    Given accept type and Content type is JSON
    And request json body is:
    {
    "name": "xxx",
    "email": "xxx@xxx.com",
    "password": "xxx",
    }
    When user sends POST request
    Then status code 200
    And content type should be application/json
    And json payload/response/body should contain:
    a new generated id that is special for user
    name
    email
    ...
     */
//third way
    //java custom class

    @Test
    public void test3(){
       // PostRegisterClass postRegisterClass=new PostRegisterClass("xxxx6116","xxxxx@61xx.com","xxxx");//bu da olur...

        PostRegisterClass postRegisterClass1=new PostRegisterClass();
        postRegisterClass1.setName("xxxxx6161x");
        postRegisterClass1.setEmail("dddd6161@ssd.com");
        postRegisterClass1.setPassword("dfdfdfdfdf");

        Response response = RestAssured
                .given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(postRegisterClass1)
                .when()
                .post("https://www.krafttechexlab.com/sw/api/v1/allusers/register");

        Assert.assertEquals(response.statusCode(),200);

        String actualName=response.path("name");

        Assert.assertEquals(actualName,"xxxxx6161x");

        Assert.assertEquals(response.path("email"),"dddd6161@ssd.com");

        Assert.assertNotNull(response.path("id"));

        System.out.println("response.path(\"id\") = " + response.path("id"));
    }

}
