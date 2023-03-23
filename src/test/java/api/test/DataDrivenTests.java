package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndPoints;
import api.payloads.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DataDrivenTests {
// using first data provider method, will create multiple users by sending post req. creating entire data
// we are going to pass only user names from dataprovider method2 and going to delete users. deleting the data using usernames.
	
	@Test(priority=1, dataProvider="Data", dataProviderClass=DataProviders.class)
	public void testPostuser(String userID,String userName,String fname,String lname,String useremail,String pwd,String Ph) {
	
		User userPayload = new User();
		
		userPayload.setId(Integer.parseInt(userID));
		userPayload.setUsername(userName);
		userPayload.setFirstName(fname);
		userPayload.setLastName(lname);
		userPayload.setEmail(useremail);
		userPayload.setPassword(pwd);
		userPayload.setPhone(Ph);
		
		Response response = UserEndPoints.createUser(userPayload);
		//response.then().log().all();// not req which will create so much of data
		Assert.assertEquals(response.getStatusCode(),200);
	}
    
	@Test(priority=2, dataProvider="UserNames", dataProviderClass=DataProviders.class)
	public void testDeleteUserByName(String userName) {
		Response response = UserEndPoints.deleteUser(userName);
        Assert.assertEquals(response.getStatusCode(),200);	
	}	
}
