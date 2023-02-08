package erenHocaAPI;

import erenHocaAPI.apiPojoTemplates.Education;
import erenHocaAPI.apiPojoTemplates.User;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;

public class day6_POJO_deserailize_Users {
    /* TASK
    base url = https://www.krafttechexlab.com/sw/api/v1
    end point /allusers/getbyid/{id}
    id parameter value is 1
    send the GET request
    then status code should be 200
    get all data into a custom class (POJO) by de-serilization
    */

    @Test
    public void test1(){
        Response response = RestAssured
                .given()
                .pathParam("id", 1)
                .when()
                .get("https://www.krafttechexlab.com/sw/api/v1/allusers/getbyid/{id}");
        //de-serialization
        //POJO-->Plain Old Java Object
        //get the data and put inside the custom java class (POJO)

        User []user=response.body().as(User[].class);
        System.out.println("user.length = " + user.length);

        System.out.println("user[0].getId() = " + user[0].getId());
        System.out.println("user[0].getName() = " + user[0].getName());
        System.out.println("user[0].getSkills().get(0) = " + user[0].getSkills().get(0));
        List<Education> education = user[0].getEducation();
        System.out.println("education.get(0) = " + education.get(0));
        Education education1 = education.get(0);
        System.out.println("education1.getDescription() = " + education1.getDescription());
        System.out.println("education1.getSchool() = " + education1.getSchool());

        for (Education education2 : education) {
            System.out.println("education2 = " + education2);
        }

    }
    @Test
    public void test2(){
        Response response=RestAssured.given()
                .contentType(ContentType.JSON)
                .relaxedHTTPSValidation()
                .get("https://api.got.show/api/continents");
        System.out.println(response.asString());
        System.out.println("response.statusCode() = " + response.statusCode());
    }

}
