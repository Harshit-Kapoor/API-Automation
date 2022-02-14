import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import files.Payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class NewTest {

	public static void main(String args[]) throws IOException {
		
		RestAssured.baseURI = "http://216.10.245.166";
		
		// Add Place
		// Taking Input of body from a static json file
		
		String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
		.body(new String(Files.readAllBytes(Paths.get("C:\\Users\\hakapoor\\eclipse-workspace\\TestProject\\src\\files\\addPlace.json"))))
		.when().post("maps/api/place/add/json")
		.then().assertThat().statusCode(200).body("scope", equalTo("APP"))
		.header("Server", "Apache").extract().asString();
		
		System.out.println(response);
		JsonPath js = new JsonPath(response);
		String placeId = js.getString("place_id");
		System.out.println(placeId);

	}
	
}
