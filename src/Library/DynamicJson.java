package Library;

import org.testng.annotations.Test;

import files.Payload;
import files.ReUsableMethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

public class DynamicJson {
		
		public static void main(String args[]) {
		
		RestAssured.baseURI = "http://216.10.245.166";
		
		String addResponse = given().log().all().header("Content-Type", "application/json")
		.body(Payload.AddBook("Qwerty","6465"))
		.when().post("/Library/Addbook.php")
		.then().log().all().statusCode(200).extract().response().asString();
		
		JsonPath js = ReUsableMethods.rawToJson(addResponse);
		String id = js.getString("ID");
		
		System.out.println(id);
		
		String deleteResponse = given().log().all().header("Content-Type", "application/json")
		.body(Payload.DeleteBook(id))
		.when().post("/Library/DeleteBook.php")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();
		
		System.out.println(deleteResponse);
		
	}
	

}
