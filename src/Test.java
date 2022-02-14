import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Test {

	public static void main(String args[]) {

		RestAssured.baseURI = "http://216.10.245.166";
		
		given().log().all().queryParam("key", "qaclick123").queryParam("place_id", "549f476875641cfa96ae3e1779dda59b")
		.when().get("maps/api/place/get/json")
		.then().assertThat().log().all().statusCode(200);
		
		
		
		
	}
	
}
