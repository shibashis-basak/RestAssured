package restAndSoap;

import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import io.restassured.response.Response;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class StaticImport {
	
	@Test
		void test_01() {
			
			Response response = given().
					headers("x-api-key","reqres_0d9eae8bc1264c7a871ced5d532a637f").
					get("https://reqres.in/api/users?page=2");
			
			System.out.println(response.asString());
			System.out.println(response.getBody().asString());
			System.out.println(response.getStatusCode());
			System.out.println(response.getStatusLine());
			System.out.println(response.getHeader("content-type"));
			System.out.println(response.getTime());
			
			int statusCode = response.getStatusCode();
			
			Assert.assertEquals(statusCode, 200);
			
		}
		
		@Test
		void test_02() {
			
			given()
				.headers("x-api-key","reqres_0d9eae8bc1264c7a871ced5d532a637f")
				.get("https://reqres.in/api/users?page=2")
			.then()
				.statusCode(200)
				.body("data.id[0]", equalTo(7));
		}

}
