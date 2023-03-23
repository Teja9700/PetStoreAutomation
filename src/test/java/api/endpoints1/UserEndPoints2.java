package api.endpoints1;
// userendpoints.java
// created for performing create, read, update, delete requests to user API.
// to create user data, we need some payload(req body-- when ever we send req we need body. 

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.ResourceBundle;

import api.payloads.User;

public class UserEndPoints2 {
// additional method created for getting URL's from routes.properties file
	static ResourceBundle getURL()
	{
		ResourceBundle routes=ResourceBundle.getBundle("routes");// Dot properties and extensions not needed and here routes is name of properties file
	    return routes;  
	}
	
	
	public static Response createUser(User payload){
        String post_url = getURL().getString("post_url");//using properties file
		Response response=given()
				.contentType(ContentType.JSON)// will get from swagger file
				.accept(ContentType.JSON)// will get from swagger file
				.body(payload)
				.when()
				.post(post_url);// passing url path here
		return response;
	}
	public static Response readUser(String username){
        String get_url = getURL().getString("get_url");//using properties file
		Response response=given()
				             .pathParam("username", username)
				.when()
				.get(get_url);// passing url path here
		return response;
	}
	public static Response updateUser(String username, User payload){
        String update_url = getURL().getString("update_url");//using properties file
		Response response=given()
				.contentType(ContentType.JSON)// will get from swagger file
				.accept(ContentType.JSON)// will get from swagger file
				.pathParam("username", username)
				.body(payload)
				.when()
				.put(update_url);// passing url path here
		return response;
	}
	public static Response deleteUser(String username){
		 String delete_url = getURL().getString("delete_url");    //using properties file
		Response response=given()
				             .pathParam("username", username)
				.when()
				.delete(delete_url);  // passing url path here
		return response;
	}
}