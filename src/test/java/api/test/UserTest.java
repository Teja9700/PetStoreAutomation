package api.test;

import static org.testng.Assert.assertEquals;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.payloads.User;
import io.restassured.response.Response;

public class UserTest {
    // normal test cases not data driven test cases
	
	
	
	Faker faker;
	User userPayload;
	public Logger logger;// for logs
	
	@BeforeClass// this will generate data
	public void setUpData() {
		/* By using faker library will generate data and that particular data will pass to pojo class
	       so that the data is ready and that will pass through post req*/
	faker=new Faker();
	userPayload = new User();
	
	userPayload.setId(faker.idNumber().hashCode());// hashcode -- generates random number
	userPayload.setUsername(faker.name().username());
	userPayload.setFirstName(faker.name().firstName());
	userPayload.setLastName(faker.name().lastName());
	userPayload.setEmail(faker.internet().safeEmailAddress());
	userPayload.setPassword(faker.internet().password(5, 10));
	userPayload.setPhone(faker.phoneNumber().cellPhone());
	
	
	// to initiate logs this method is usefull
	logger = LogManager.getLogger(this.getClass());
	}
	@Test(priority=1)
	public void testPostUser() {
		logger.info("*************Creating User************");
		Response response = UserEndPoints.createUser(userPayload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(),200);
		logger.info("*************User is Created************");

	}
	
	@Test(priority=2)
	public void testGetUserByName() {
		logger.info("*************Reading User Info************");
		Response response = UserEndPoints.readUser(this.userPayload.getUsername());
	    response.then().log().all();
	    Assert.assertEquals(response.getStatusCode(), 200);
	    logger.info("************User Info IS Displayed************");
	}
	@Test(priority=3)
	public void testUpdateUserByName() {
		logger.info("************Updating User Info************");
		//update data using payload
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		
		Response response = UserEndPoints.updateUser(this.userPayload.getUsername(), userPayload);
		response.then().log().body().statusCode(200);//chai assertion in restassured
		Assert.assertEquals(response.getStatusCode(),200);//testng assertion
	// checking data after update
		Response responseAfterUpdate = UserEndPoints.readUser(this.userPayload.getUsername());
		responseAfterUpdate.then().log().all();
	    Assert.assertEquals(responseAfterUpdate.getStatusCode(), 200);
	    logger.info("************User Info IS Updated************");
	}
	@Test(priority=4)
	public void testDeleteUserByName() {
		logger.info("************deleting user Info************");
		Response response = UserEndPoints.deleteUser(this.userPayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("************User Info IS Deleted************");
	}
	
	
	
}
