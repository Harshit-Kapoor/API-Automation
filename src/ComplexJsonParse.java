import org.testng.Assert;

import files.Payload;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {

	public static void main(String args[]) {

		JsonPath js = new JsonPath(Payload.Courses());

		// Get Count of Courses
		int count = js.getInt("courses.size()");
		System.out.println(count);

		// Get Purchase Amount
		int totalAmount = js.getInt("dashboard.purchaseAmount");
		System.out.println(totalAmount);

		// Get Title of first course
		String title = js.getString("courses[0].title");
		System.out.println(title);

		// Get Titile name and price for all courses
		for (int i = 0; i < count; i++) {
			System.out.println(js.getString("courses[" + i + "].title"));
			System.out.println(js.getInt("courses[" + i + "].price"));
		}

		//Print the copies for RPA
		for(int i=0; i < count; i++) {
			if(js.getString("courses[" + i + "].title").equals("RPA")) {
				System.out.println(js.getInt("courses[" + i + "].copies"));		
				break;
			}	
		}
		
		// Sum amount validation
		
		int sum = 0;
		
		for(int i=0; i < count; i++) {
			
			int price = js.getInt("courses[" + i + "].price");
			int copies = js.getInt("courses[" + i + "].copies");			
			int courseAmount = price * copies;
			sum = sum + courseAmount;
			System.out.println(courseAmount);
		}
		
		Assert.assertEquals(sum, totalAmount);
		
	}

}
