package api.tests;

import api.endpoints.UserEndPoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import org.apache.logging.log4j.*;

public class DataDrivenTests {

    public Logger logger;
    @BeforeClass
    public void setup()
    {
        logger = LogManager.getLogger(this.getClass());
    }
    @Test(priority = 1, dataProvider = "Data", dataProviderClass = DataProviders.class)
    public void testPostUser(String userID, String userName, String fName, String lName,
                             String userEmail, String passWord, String phoneNo){
        logger.info("******************Creating User*********************");
        User userPayload = new User();
        userPayload.setId(Integer.parseInt(userID));
        userPayload.setUsername(userName);
        userPayload.setFirstName(fName);
        userPayload.setLastName(lName);
        userPayload.setEmail(userEmail);
        userPayload.setPassword(passWord);
        userPayload.setPhone(phoneNo);

        Response response = UserEndPoints.createUser(userPayload);
        Assert.assertEquals(response.getStatusCode(), 200);
        logger.info("******************User Creation Executed*********************");

    }
    @Test(priority = 2, dataProvider = "UserNames", dataProviderClass = DataProviders.class)
    public void testGetUserByName(String userName){
        logger.info("******************Get User*********************");

        Response response = UserEndPoints.readUser(userName);
        Assert.assertEquals(response.getStatusCode(), 200);
        logger.info("******************Get User Executed *********************");

    }

    @Test(priority = 3, dataProvider = "UserNames", dataProviderClass = DataProviders.class)
    public void testDeleteUserByName(String userName){
        logger.info("******************Delete User*********************");

        Response response = UserEndPoints.deleteUser(userName);
        Assert.assertEquals(response.getStatusCode(), 200);
        logger.info("******************User Deletion Executed*********************");

    }
}
