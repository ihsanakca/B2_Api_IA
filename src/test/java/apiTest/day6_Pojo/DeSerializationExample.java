package apiTest.day6_Pojo;

import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

public class DeSerializationExample {
    @BeforeClass
    public void beforeClass() {
        baseURI= "https://www.krafttechexlab.com/sw/api/v1";
    }

    @Test
    public void task1(){
        /**
         * end point --> /allusers/alluser -->get all user
         * page size=50
         * page=2
         * The company in the 8. users experience part
         * verify this information
         * 1.company "Ghan IT Com"
         * 2.company "GHAN II IT BV"
         */

        Response response= given().accept(ContentType.JSON)
                .queryParam("pagesize",50)
                .queryParam("page",2)
                .when().get("/allusers/alluser");

        Assert.assertEquals(response.statusCode(),200);

        //de-serialization  Json to java coolection

        List <Map<String, Object>> allUsers = response.body().as(List.class);

        //System.out.println("allUsers = " + allUsers);

        System.out.println("allUsers.size() = " + allUsers.size());

        List<Map<String,Object>> experienceUser= (List<Map<String, Object>>) allUsers.get(7).get("experience");

        System.out.println("experienceUser = " + experienceUser);

        String company1= (String) experienceUser.get(0).get("company");
        Assert.assertEquals(company1,"Ghan IT Com","FAILED check company1");
        System.out.println("company1 = " + company1);

        String company2= (String) experienceUser.get(1).get("company");
        Assert.assertEquals(company2,"GHAN II IT BV","FAILED check company2");
        System.out.println("company2 = " + company2);

        List<Map<String,Object>> educationUser= (List<Map<String, Object>>) allUsers.get(7).get("education");
        String school= (String) educationUser.get(0).get("school");

        List<String> skills= (List<String>) allUsers.get(7).get("skills");
        System.out.println("skills = " + skills);

        System.out.println("school = " + school);

        //x[3].education[0].degree
        List<Map<String,Object>> educationUser2= (List<Map<String, Object>>) allUsers.get(3).get("education");
        System.out.println("educationUser2.get(0).get(\"degree\") = " + educationUser2.get(0).get("degree"));



    }

    @Test
    public void test2(){
        Response response = given().accept(ContentType.JSON)
                .pathParam("id", 110)
                .when().get("/allusers/getbyid/{id}");

        Assert.assertEquals(response.statusCode(),200);

        List<Map<String,Object>> user110=response.body().as(List.class);

        //System.out.println("user110 = " + user110);

        List<Map<String,Object>> experience = (List<Map<String, Object>>) user110.get(0).get("experience");
        String company = (String) experience.get(1).get("company");
        System.out.println("company = " + company);


    }
}
