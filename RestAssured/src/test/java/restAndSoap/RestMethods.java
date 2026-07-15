package restAndSoap;

import static org.testng.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.internal.path.json.mapping.JsonObjectDeserializer;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;

public class RestMethods {
	
	//@Test
		public void test_01() {

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

		//@Test
		public void test_02() {

			given()
			.headers("x-api-key","reqres_0d9eae8bc1264c7a871ced5d532a637f")
			.get("https://reqres.in/api/users?page=2")
			.then()
			.statusCode(200)
			.body("data.id[0]", equalTo(9))
			.log().all();
		}


		//@Test
		void test_03() {

			given()
			.headers("x-api-key","reqres_0d9eae8bc1264c7a871ced5d532a637f")
			.get("https://reqres.in/api/users?page=2")
			.then()
			.statusCode(200)
			.log().all();
		}

		//@Test
		void test_04() {

			given()
			.headers("x-api-key","reqres_0d9eae8bc1264c7a871ced5d532a637f")
			.get("https://reqres.in/api/users?page=2")
			.then()
			.statusCode(200)
			.body("data.first_name", hasItems("Michael", "Lindsay"))
			.log().all();
		}

		//@Test
		void test_05() {

			given()
			.headers("x-api-key","reqres_0d9eae8bc1264c7a871ced5d532a637f")
			.param("key", "value")
			.header("key", "value")
			.when()
			.get("https://reqres.in/api/users?page=2")
			.then()
			.statusCode(200)
			.body("data.first_name", hasItems("Michael", "Lindsay"))
			.log().all();
		}

		//@Test
		void test_06() {

			given()
			.get("http://localhost:3000/user/1")
			.then()
			.statusCode(200)
			.log().all(); 
		}

		@Test
		public void test_post() {

			//Map<String, Object> jsonMap = new HashMap<String, Object>();

			//jsonMap.put("name", "Raghav");
			//jsonMap.put("job", "Teacher");
			
			JSONObject request = new JSONObject();
			

			request.put("name", "Raghav");
			request.put("job", "Teacher");
			
			System.out.println("JSON request is : "+request);

			given().
			contentType(ContentType.JSON).
			accept(ContentType.JSON).
			header("Content-Type","application/json").
			headers("x-api-key","reqres_0d9eae8bc1264c7a871ced5d532a637f").
			body(request.toJSONString()).

			when().
			post("https://reqres.in/api/users").
			then().
			statusCode(201);
			//body("", hasItems("Raghav"));
		}


		//@Test
		public void test_put() {


			JSONObject request = new JSONObject(); 

			request.put("name", "Raghav");
			request.put("job", "Teacher");

			given().
			contentType(ContentType.JSON).
			accept(ContentType.JSON).
			header("Content-Type","application/json").
			headers("x-api-key","reqres_0d9eae8bc1264c7a871ced5d532a637f").
			body(request.toJSONString()).

			when().
			put("https://reqres.in/api/users/2").
			then().
			statusCode(200);
			//body("", hasItems("Raghav"));
		}

		//@Test
		public void test_patch() {


			JSONObject request = new JSONObject(); 

			request.put("name", "Raghav");
			request.put("job", "Teacher");

			given().
			contentType(ContentType.JSON).
			accept(ContentType.JSON).
			header("Content-Type","application/json").
			headers("x-api-key","reqres_0d9eae8bc1264c7a871ced5d532a637f").
			body(request.toJSONString()).

			when().
			patch("https://reqres.in/api/users/2").
			then().
			statusCode(200);
			//body("", hasItems("Raghav"));
		}

		//@Test
		public void test_delete() {

			given().
			headers("x-api-key","reqres_0d9eae8bc1264c7a871ced5d532a637f").
			when().
			delete("https://reqres.in/api/users/2").
			then().
			statusCode(204);
			//body("", hasItems("Raghav"));
		}

}
