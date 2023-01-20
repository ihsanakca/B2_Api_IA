package erenHocaAPI;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class Day3_getRequestWithParameter {
    String kraftBaseURL="https://www.krafttechexlab.com/sw/api/v1";

    @Test
    public void getRequestWithPathParameter(){
    //old way
        Response response = RestAssured
                .when()
                .get(kraftBaseURL + "/allusers/getbyid/1");

        //how to provide path parameter inside request
        //new way

        Response response1 = RestAssured
                .given()
                .pathParam("id", 1)
                .and()
                .accept(ContentType.JSON)
                .when()
                .get(kraftBaseURL + "/allusers/getbyid/{id}");

        Assert.assertEquals(response1.statusCode(),200);
        Assert.assertTrue(response1.body().asString().contains("afmercan@gmail.com"));

    }
    @Test
    public void getRequestWithQueryParameter(){
        Response response = RestAssured
                .given()
                .queryParam("pagesize", 10)
                .queryParam("page", 1)
                .when()
                .get(kraftBaseURL + "/allusers/alluser");

        Assert.assertEquals(response.statusCode(),200);
        Assert.assertTrue(response.body().asString().contains("Manual Tester"));

    }

    @Test
    public void getRequestWithQueryParameterWithMap(){
        Map<String,Object> map=new HashMap<>();
        map.put("pagesize",10);
        map.put("pages",1);


        Response response = RestAssured
                .given()
//                .queryParam("pagesize", 10)
//                .queryParam("page", 1)
                .queryParams(map)
                .when()
                .get(kraftBaseURL + "/allusers/alluser");

        Assert.assertEquals(response.statusCode(),200);
        Assert.assertTrue(response.body().asString().contains("Manual Tester"));
    }
}
