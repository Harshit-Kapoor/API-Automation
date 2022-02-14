import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

import files.Payload;
import files.ReUsableMethods;


public class Places {
	public static void main(String args[]) {
		
		RestAssured.baseURI = "http://216.10.245.166";
		
		// Add Place
		
		String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
		.body(Payload.AddPlace())
		.when().post("maps/api/place/add/json")
		.then().assertThat().statusCode(200).body("scope", equalTo("APP"))
		.header("Server", "Apache").extract().asString();
		
		System.out.println(response);
		JsonPath js = new JsonPath(response);
		String placeId = js.getString("place_id");
		System.out.println(placeId);
		
		// Update Place
		String newAddress = "8/22 WPN, New Delhi";
		
		given().log().all().queryParam("key", "qaclick123").queryParam("placeId", placeId).header("Content-Type", "application/json")
		.body("{\r\n"
				+ "\"place_id\":\""+placeId+"\",\r\n"
				+ "\"address\":\""+newAddress+"\",\r\n"
				+ "\"key\":\"qaclick123\"\r\n"
				+ "}\r\n"
				+ "")
		.when().put("maps/api/place/update/json")
		.then().log().all().assertThat().statusCode(200).body("msg", equalTo("Address successfully updated"));
		
		// Get Place
		
		String getPlaceResponse = given().log().all().queryParam("key", "qaclick123")
		.queryParam("place_id", placeId)
		.when().get("maps/api/place/get/json")	
		.then().assertThat().log().all().statusCode(200).extract().response().asPrettyString();
		
		
		JsonPath jpath = ReUsableMethods.rawToJson(getPlaceResponse);
		String actualAddress = jpath.getString("address");
		
		Assert.assertEquals(newAddress, actualAddress);
		
		
	}
		
}
