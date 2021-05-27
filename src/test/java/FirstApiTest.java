import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;


import java.io.File;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;

public class FirstApiTest {

    @Test
    public void getSingleUserResourceTest(){
        Response response = given()
                .when().get("https://reqres.in/api/unknown/2");
        File jsonSchema = new File("src/test/resources/schemas/getSingleUserResource.json");
        response.then().statusCode(200).assertThat().body(matchesJsonSchema(jsonSchema));
        System.out.println("getSingleUserResourceTest Status Code is: " + response.statusCode());
        System.out.println(" 123 ");
    }

    @Test
    public  void getSingleUserTest(){
        File jsonSchema = new File("src/test/resources/schemas/getSingleUser.json");
        Response response = given()
                .when().get("https://reqres.in/api/users/2");
        response.then().statusCode(200).assertThat().body(matchesJsonSchema(jsonSchema));
        System.out.println("getSingleUserTest Status Code is: " + response.statusCode());
    }

    @Test
    public  void getSingleUserNotFoundTest(){
        Response response = given()
                .when().get("https://reqres.in/api/users/23");
        String jsonSchema = "{}";
        response.then().statusCode(404).assertThat().body(matchesJsonSchema(jsonSchema));
        System.out.println("getSingleUserNotFoundTest Status Code is: " + response.statusCode());
    }

    @Test
    public void postCreateUserTest(){
        RestAssured.baseURI = "https://reqres.in/api/users";
        File jsonCreateUser = new File("src/test/resources/postCreateUser.json");
        System.out.println("postCreateUserTest body is: ");
        RestAssured.given().body(jsonCreateUser).post().then().log().body().statusCode(201);
    }
}
