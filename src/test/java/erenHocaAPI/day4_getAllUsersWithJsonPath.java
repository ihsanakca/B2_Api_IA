package erenHocaAPI;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

public class day4_getAllUsersWithJsonPath {
    /*
    baseURL="https://www.krafttechexlab.com/sw/api/v1"
     endpoint="/allusers/alluser"
     */
    @Test
    public void getAllUsers() {

        Response response = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .queryParam("page", 1)
                .queryParam("pagesize", 10)
                .when()
                .get("https://www.krafttechexlab.com/sw/api/v1/allusers/alluser");

        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(response.contentType(), "application/json; charset=UTF-8");

        //response.prettyPrint();

        //get first id
        int id = response.path("id[0]");
        System.out.println("id = " + id);

        //get first id with jsonPath

        JsonPath jsonPath = response.jsonPath();

        int idWithJsonPath = jsonPath.getInt("id[0]");
        System.out.println("idWithJsonPath = " + idWithJsonPath);

        //get the second id
        int secondId = jsonPath.getInt("id[1]");
        System.out.println("secondId = " + secondId);

        //get all names
        List<String> names = jsonPath.getList("name");
        System.out.println("names = " + names);

        //get all IDs

        List<Integer> allIds = jsonPath.getList("id");
        System.out.println("allIds = " + allIds);

        //get the skills of first user
        List<String> firstUserSkills = jsonPath.getList("skills[0]");
        System.out.println("firstUserSkills = " + firstUserSkills);
        System.out.println("firstUserSkills.get(0) = " + firstUserSkills.get(0));
        System.out.println("firstUserSkills.get(1) = " + firstUserSkills.get(1));

        //get first user first skill
        String firstUserFirstSkill = jsonPath.getString("skills[0][0]");
        System.out.println("firstUserFirstSkill = " + firstUserFirstSkill);

        //get first user second skill
        String firstUserSecondSkill = jsonPath.getString("skills[0][1]");
        System.out.println("firstUserSecondSkill = " + firstUserSecondSkill);

        //get fisrt id of first education of first user
        int firstUserOfFirstEducationID=jsonPath.getInt("education[0].id[0]");
        System.out.println("firstUserOfFirstEducationID = " + firstUserOfFirstEducationID);

        //second way
        Map<String,Object>firstUserEducationMap= jsonPath.getMap("education[0][0]");
        System.out.println("firstUserEducationMap = " + firstUserEducationMap);
        System.out.println("firstUserEducationMap.get(\"id\") = " + firstUserEducationMap.get("id"));

        //get all users all skills
        List<List<String>> allUsersAllSkills=jsonPath.getList("skills");
        System.out.println("allUsersAllSkills = " + allUsersAllSkills);

        System.out.println("allUsersAllSkills.get(0).get(0) = " + allUsersAllSkills.get(0).get(0));
        System.out.println("allUsersAllSkills.get(9).get(2) = " + allUsersAllSkills.get(9).get(2));

    }
}
