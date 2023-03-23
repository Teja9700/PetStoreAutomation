package api.endpoints;

/* we will maintain here only URL's. from swagger URI will get all these url's
Swagger URI: https://petstore.swagger.io  (main url or root) and 
from https: to v2 is base url and from /user -- are endpoints.
all below are belong to user module:
Create user(Post): https://petstore.swagger.io/v2/user
Get user(Get): https://petstore.swagger.io/v2/user/{username}
Update user(Put): https://petstore.swagger.io/v2/user/{username}
Delete user(Delete): https://petstore.swagger.io/v2/user/{username}
*/
public class Routes {

	public static String base_url = "https://petstore.swagger.io/v2";
	
	//user module
	public static String post_url= base_url+"/user";
	public static String get_url= base_url+"/user/{username}";
	public static String update_url= base_url+"/user/{username}";
	public static String delete_url= base_url+"/user/{username}";
	
//{username} -- path parameter y? first we need to send post req that will create username. 
//based on that will pass username in other requests. we cant hardcore this. its a chaining process.
	
	
	//store module
	//create store module url's
	
	//pet module
	//create pet module url's
}
