package apiTest.day06_Pojo;

import com.google.gson.Gson;
import org.testng.annotations.Test;

import java.util.Map;

public class GsonTest {
    /*
 { "id": 9222968140497198741,
    "username": "Jake23",
    "firstName": "Jake",
    "lastName": "Master",
    "email": "jake@gmail.com",
    "password": "Test1234",
    "phone": "55512345",
    "userStatus": 21
    }
 */

    @Test
    public void jsonToMap(){
        Gson gson=new Gson();
        String myJsonBody="{ \"id\": 9222968140497198741,\n" +
                "    \"username\": \"Jake23\",\n" +
                "    \"firstName\": \"Jake\",\n" +
                "    \"lastName\": \"Master\",\n" +
                "    \"email\": \"jake@gmail.com\",\n" +
                "    \"password\": \"Test1234\",\n" +
                "    \"phone\": \"55512345\",\n" +
                "    \"userStatus\": 21\n" +
                "    }";
        System.out.println("myJsonBody = " + myJsonBody);

        //gson convert to map
        Map<String,Object> dataMap=gson.fromJson(myJsonBody,Map.class);

        System.out.println("dataMap = " + dataMap);

        System.out.println("dataMap.get(\"email\") = " + dataMap.get("email"));

        //gson converting to object class

        PetStoreUser oneUser=gson.fromJson(myJsonBody,PetStoreUser.class);
        System.out.println("oneUser = " + oneUser);

        //serialization
        //Java collection or pojo  to json

        PetStoreUser petStoreUser=new PetStoreUser(11,"Jake23","Jake","Master",
        "jake@gmail.com","Test1234","55512345",21);

        String jsonUser=gson.toJson(petStoreUser);
        System.out.println("jsonUser = " + jsonUser);
    }
}
