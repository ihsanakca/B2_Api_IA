package apiTest.day5_HamcrestMatcher;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class JsonToJavaCollection {

    @BeforeClass
    public void beforeClass() {
        baseURI= "https://www.krafttechexlab.com/sw/api/v1";
    }

    @Test
    public void userToMap(){
        Response response = given().accept(ContentType.JSON)
                .when().get("https://demoqa.com/Account/v1/User/11");

        Assert.assertEquals(response.statusCode(),401);

        Map<String,Object> jsonMap=response.body().as(Map.class);

        System.out.println("jsonMap = " + jsonMap);


        //verify message

        String message= (String) jsonMap.get("message");
        System.out.println("message = " + message);

        Assert.assertEquals(message,"User not authorized!");

        //verify code
        String code=(String) jsonMap.get("code");
        System.out.println("code = " + code);
        Assert.assertEquals(code,"1200");
    }

    @Test
    public void AllUsersToMap(){
        Response response=given().accept(ContentType.JSON)
                .queryParam("pagesize",50)
                .queryParam("page",1)
                .when().get("/allusers/alluser");

        Assert.assertEquals(response.statusCode(),200);

        //we need to de-serialiaze Json response a java collection
        //birden fazla json body olduğundan list of map yapmamız gerekir..

        List<Map<String,Object>> allusersListMap=response.body().as(List.class);

       // System.out.println("allusersListMap = " + allusersListMap);

        //ikinci kullanıcının adını assert edelim

        String name = (String) allusersListMap.get(1).get("name");
        System.out.println("name = " + name);

        Assert.assertEquals(name,"isa akyuz");

        System.out.println("allusersListMap.get(0).get(\"skills\") = " + allusersListMap.get(0).get("skills"));

        List<String > skillsList= (List<String>) allusersListMap.get(0).get("skills");
        System.out.println("skillsList = " + skillsList);
        Assert.assertEquals(skillsList.get(0),"PHP");

        List<Map<String,Object>> experienceMapList= (List<Map<String, Object>>) allusersListMap.get(0).get("experience");

        System.out.println("experienceMapList = " + experienceMapList);

        System.out.println("experienceMapList.get(1).get(\"job\") = " + experienceMapList.get(1).get("job"));


    }
}
