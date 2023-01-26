package apiTest.day04_jsonPath;
import static io.restassured.RestAssured.*;

import io.restassured.path.json.JsonPath;
import org.testng.annotations.BeforeClass;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.baseURI;

public class UserWith_JsonPath {
    @BeforeClass
    public void beforeClass() {
        baseURI= "https://www.krafttechexlab.com/sw/api/v1";
    }
    /*
    TASK
    Given accept type is json
    And Path param user id is 111
    When user sends a GET request to /allusers/getbyid/{id}
    Then the status Code should be 200
    And Content type json should be "application/json; charset=UTF-8"
    And user's name should be Thomas Eduson
    And user's id should be 111
    And user's email should be thomas@test.com
   */

    @Test
    public void jsonPathTest(){
        Response response=given().contentType(ContentType.JSON)
                .pathParam("id",111)
                .when()
                .get("/allusers/getbyid/{id}");

        Assert.assertEquals(response.statusCode(),200);
        Assert.assertEquals(response.contentType(),"application/json; charset=UTF-8");

        JsonPath jsonPath=response.jsonPath();

        int idJson=jsonPath.getInt("id[0]");
        String nameJson=jsonPath.getString("name[0]");
        String emailJson=jsonPath.getString("email[0]");

        Assert.assertEquals(idJson,111);
        Assert.assertEquals(nameJson,"Thomas Eduson");
        Assert.assertEquals(emailJson,"thomas@test.com");

        System.out.println("idJson = " + idJson);
        System.out.println("nameJson = " + nameJson);
        System.out.println("emailJson = " + emailJson);

        System.out.println("response.path(\"id[0]\") = " + response.path("id[0]"));

        Assert.assertEquals((int) response.path("id[0]"),111);
    }
    /*
    TASK
    Given accept type is json
    When user sends a GET request to /allusers/alluser
    Then the status Code should be 200
    And Content type json should be "application/json; charset=UTF-8"
    And second name should be isa akyuz
    And first user's experience jobs should be Junior Developer1, Junior Developer1, Junior Developer
     */

    @Test
    public void jsonPathTest2(){
        Response response=given().contentType(ContentType.JSON)
                .queryParam("pagesize",50)
                .queryParam("page",1)
                .when()
                .get("/allusers/alluser");

        Assert.assertEquals(response.statusCode(),200);
        Assert.assertEquals(response.contentType(),"application/json; charset=UTF-8");

        JsonPath jsonPath=response.jsonPath();

        String secondName=jsonPath.getString("name[1]");
        Assert.assertEquals(secondName,"isa akyuz");

        List<String> jobs=jsonPath.getList("experience.job[0]");

        List<String> expectedJobs=new ArrayList<>();
        expectedJobs.add("Junior Developer1");
        expectedJobs.add("Junior Developer1");
        expectedJobs.add("Junior Developer");

        Assert.assertEquals(jobs,expectedJobs);

        String job = jsonPath.getString("experience.job[0]");
        Assert.assertEquals(job,"[Junior Developer1, Junior Developer1, Junior Developer]");

        System.out.println("secondName = " + secondName);
        System.out.println("jobs = " + jobs);
        System.out.println("expectedJobs = " + expectedJobs);
        System.out.println("job = " + job);


    }
    /*
        TASK
        Given accept type is json
        And Path param user id is 111
        When user sends a GET request to /allusers/getbyid/{id}
        Then the status Code should be 200
        And Content type json should be "application/json; charset=UTF-8"
        Get user skills
        And user's first skill should be PHP

       */
    @Test
    public void jsonPathTest3(){
        Response response=given().contentType(ContentType.JSON)
                .pathParam("id",111)
                .when()
                .get("/allusers/getbyid/{id}");

        Assert.assertEquals(response.statusCode(),200);
        Assert.assertEquals(response.contentType(),"application/json; charset=UTF-8");

        JsonPath jsonPath=response.jsonPath();

        List<String> skills=jsonPath.getList("skills[0]");

        System.out.println("skills = " + skills.get(0));
        System.out.println("skills = " + skills);

        Assert.assertEquals(skills.get(0),"PHP");

        String skills2=jsonPath.getString("skills[0][0]");
        String skills3=jsonPath.getString("skills[0][1]");
        String skills4=jsonPath.get("skills[0][1]");

        System.out.println("skills2 = " + skills2);
        System.out.println("skills3 = " + skills3);
        System.out.println("skills4 = " + skills4);

    }
}
