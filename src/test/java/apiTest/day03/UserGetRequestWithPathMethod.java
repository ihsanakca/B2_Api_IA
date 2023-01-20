package apiTest.day03;

import static io.restassured.RestAssured.*;

import groovy.util.logging.Slf4j;
import io.restassured.RestAssured;
        import io.restassured.http.ContentType;
        import io.restassured.response.Response;
        import org.testng.Assert;
        import org.testng.annotations.BeforeClass;
        import org.testng.annotations.Test;

public class UserGetRequestWithPathMethod {
    @BeforeClass
    public void beforeClass() {
       baseURI= "https://www.krafttechexlab.com/sw/api/v1";
    }

    @Test
    public void testWithPathMethod(){
        Response response=given().accept(ContentType.JSON)
                .pathParam("id",111)
                .when().log().all()
                .get("/allusers/getbyid/{id}");

        Assert.assertEquals(response.statusCode(),200);
        Assert.assertEquals(response.contentType(),"application/json; charset=UTF-8");

        //print each value

        System.out.println("response.body().path(\"name\").toString() = " + response.body().path("name").toString());
        System.out.println("response.path(\"id\").toString() = " + response.path("id").toString());
        System.out.println("response.path(\"job\").toString() = " + response.path("job").toString());

        int id=response.path("id[0]");
//        int id1=response.path("id[0]");
        String name=response.path("name[0]");
        String jobName=response.path("job[0]");

        Assert.assertEquals(id,111);
        Assert.assertEquals(name,"Thomas Eduson");
        Assert.assertEquals(jobName,"Developer");

        System.out.println("id = " + id);
        System.out.println("name = " + name);
        System.out.println("jobName = " + jobName);
    }

    /*

    TASK
    Given accept type json
    And query  parameter value pagesize 50
    And query  parameter value page 1
    When user sends GET request to /allusers/alluser
    Then response status code should be 200
    And response content-type application/json; charset=UTF-8
    Verify the first id is 1
    Verify the first name is aFm
    Verify the last id is 102
    Verify the last name is GHAN
     */

    @Test
    public void testWithPathMethod2(){
        Response response=given().accept(ContentType.JSON)
                .queryParam("pagesize",50)
                .queryParam("page",1)
                .when()
                .get("/allusers/alluser");

        Assert.assertEquals(response.statusCode(),200);
        Assert.assertEquals(response.contentType(),"application/json; charset=UTF-8");
        Assert.assertEquals(response.getHeader("Content-Type"),"application/json; charset=UTF-8");

        int id=response.path("id[0]");
        String name=response.path("name[0]");
        int lastId=response.path("id[-1]");
        String lastName=response.path("name[-1]");

        Assert.assertEquals(id,1);
        Assert.assertEquals(name,"aFm");
        Assert.assertEquals(lastId,102);
        Assert.assertEquals(lastName,"GHAN");

        System.out.println("id = " + id);
        System.out.println("name = " + name);
        System.out.println("lastName = " + lastName);
        System.out.println("lastId = " + lastId);

    }
    /*
        Given accept type json
        When user sends a get request to https://demoqa.com/BookStore/v1/Books
        Then status code should be 200
        And content typr should be application/json; charset=utf-8
        And the first book isbn should be 9781449325862
        And the first book publisher should be O'Reilly Media

         */
    @Test
    public void testTask2(){
        Response response=given().accept(ContentType.JSON)
                .when()
                .get("https://demoqa.com/BookStore/v1/Books");

       // response.prettyPrint();

        Assert.assertEquals(response.statusCode(),200);
        Assert.assertEquals(response.contentType(),"application/json; charset=utf-8");

        String firstISBN=response.body().path("books.isbn[0]");
        String firstPublisher=response.path("books.publisher[0]");

        Assert.assertEquals(firstISBN,"9781449325862");
        Assert.assertEquals(firstPublisher,"O'Reilly Media");

        System.out.println("firstISBN = " + firstISBN);
        System.out.println("firstPublisher = " + firstPublisher);

        String lastISBN=response.path("books.isbn[-8]");
        System.out.println("lastISBN = " + lastISBN);

    }
}
