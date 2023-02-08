package erenHocaAPI;

import erenHocaAPI.apiPojoTemplates.Students;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class day6_POJO_deserialize_Students {
    //TASK
    //base url = https://gorest.co.in/
    //end point = /public/v2/users
    //path parameter = {id} --> 240530
    //send a get request with the above credentials
    //parse to Json object to pojo (custom java class)
    //verify that the body below
    /*
    {
    "id": 240530,
    "name": "Ms. Sujata Tandon",
    "email": "sujata_tandon_ms@crona.co",
    "gender": "female",
    "status": "active"
    }
     */

    @Test
    public void test1(){
        Response response = RestAssured
                .given()
                .pathParam("id", 240530)
                .when()
                .get("https://gorest.co.in/public/v2/users/{id}");

        Students student1 =response.body().as(Students.class);

        System.out.println("student1.toString() = " + student1.toString());

        System.out.println("student1.getId() = " + student1.getId());
        System.out.println("student1.getName() = " + student1.getName());
        System.out.println("student1.getEmail() = " + student1.getEmail());
        System.out.println("student1.getGender() = " + student1.getGender());
        System.out.println("student1.getStatus() = " + student1.getStatus());

    }
}
