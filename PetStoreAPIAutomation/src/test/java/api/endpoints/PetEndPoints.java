package api.endpoints;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class PetEndPoints {
    public static Response readPet(long petId){
        Response response = given().
                pathParam("petId",petId).
                contentType(ContentType.JSON).
                when().
                get(Routes.get_pet_url);
        return response;
    }
}
