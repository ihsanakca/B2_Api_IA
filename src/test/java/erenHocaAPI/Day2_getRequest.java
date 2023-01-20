package erenHocaAPI;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Day2_getRequest {
    String kraftBaseURL="https://www.krafttechexlab.com/sw/api/v1";

    @Test
    public void simpleGetRequest(){
        //make api call
        Response response = RestAssured
                .given()
                .queryParam("page", 1)
                .queryParam("pagesize", 3)
                .when()
                .get(kraftBaseURL + "/allusers/alluser");

        System.out.println("response.statusCode() = " + response.statusCode());
        response.prettyPrint();

    }

    @Test
    public void simpleGetRequest2(){
        //make api call
        Response response = RestAssured
                .when()
                .get(kraftBaseURL + "/allusers/getbyid/1");

        //verification
        Assert.assertEquals(response.statusCode(),200);
        //first way to check response body
        //response.body().asString()
        //verify that response body has "aFm"

        Assert.assertTrue(response.body().asString().contains("aFm"));
        Assert.assertTrue(response.prettyPrint().contains("aFm"));

    }

    @Test
    public void simpleGetRequest3_HamCrestMatchers(){
       //make api call
       RestAssured
                .when()
                .get(kraftBaseURL + "/allusers/getbyid/1")
                .then()
                .statusCode(200)
                .and()
                .contentType("application/json; charset=UTF-8");

    }
}
