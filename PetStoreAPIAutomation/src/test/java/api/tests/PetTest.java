package api.tests;

import api.endpoints.PetEndPoints;

import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PetTest {
    private long id = 9223372016900021000L;
    @Test(priority = 1)
    public void testHappyPath(){

        Response response = PetEndPoints.readPet(id);
        response.then().log().all();
        response.then().log().body().statusCode(200);//rest Assured assertions
    }
    @Test(priority = 2)
    public void testResponseHeader(){
        Response response = PetEndPoints.readPet(id);
        response.then().headers("access-control-allow-headers","Content-Type, api_key, Authorization ","server","Jetty(9.2.9.v20150224)");//rest Assured assertions
    }
    @Test(priority = 3)
    public void testResponseParam(){
        Response response = PetEndPoints.readPet(id);
        response.then().log().all();
        response.then().body("category.name", Matchers.equalTo("string"));//rest Assured assertions

    }
    @Test(priority = 4)
    public void testResponseTime(){
        Response response = PetEndPoints.readPet(id);
        response.then().time(Matchers.lessThan(2500l));//rest Assured assertions
    }
}
