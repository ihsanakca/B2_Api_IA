package apiTest.day5_HamcrestMatcher;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class HamcrestMatcher {

    @BeforeClass
    public void beforeClass() {
        baseURI= "https://www.krafttechexlab.com/sw/api/v1";
    }

    /*
        given accept type is json
        And path param id is 111
        When user sends a get request to /allusers/getbyid/{id}
        Then status code should be 200
        And content type should be application/json; charset=UTF-8
        And json data has following:
         "id"= 111
         "name"= "Thomas Eduson"
         "job"="Developer"
         */

    @Test
    public void oneUserWithHamcrest(){
        given().accept(ContentType.JSON)
                .and().pathParam("id",111)
                .when().get("/allusers/getbyid/{id}")
                .then().statusCode(200)
                .and().assertThat().contentType("application/json; charset=UTF-8")
                .and().body("id[0]",equalTo(111),"name[0]"
                        ,equalTo("Thomas Eduson"),"job[0]",equalTo("Developer"));
    }
    /*
        given accept type is json
        And query param pagesize is 50
        And query param page is 1
        When user sends a get request to /allusers/alluser
        Then status code should be 200
        And content type should be application/json; charset=UTF-8
        And response header content-length should be 8653
        And response header server should be Apache/2
        And response headers has Date
        And json data should have "GHAN","aFm","Sebastian"
        And json data should have "QA" for job
        And json data should have "Junior Developer1" for first user's experience job
         */
    @Test
    public void alluserWithHamcrest(){
        given().accept(ContentType.JSON)
                .queryParam("pagesize",50)
                .queryParam("page",1)
                .log().all()
                .when().get("/allusers/alluser")

                .then().statusCode(200)
                .contentType(equalTo("application/json; charset=UTF-8"))
                .and().assertThat().headers("content-length",equalTo("8653"),"server",equalTo("Apache/2"))
                .headers("server",equalTo("Apache/2"))
                .headers("Date",notNullValue())
                .body("name",hasItems("GHAN","aFm","Sebastian","Lion","eren"),
                        "job",hasItems("QA"),
                        "experience.job[0]",hasItems("Junior Developer1"),"name[0]",equalTo("MercanS"))
                .log().all();


    }

    @Test
    public void alluserWithHamcrest2(){
        Response response = given().accept(ContentType.JSON)
                .queryParam("pagesize", 50)
                .queryParam("page", 1)
                .when().get("/allusers/alluser");

        Object name = response.path("name");
        System.out.println("name = " + name);


    }

}
