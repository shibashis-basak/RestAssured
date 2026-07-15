package restAndSoap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class ValidateRestSchema {

	@Test
	void test() {

		baseURI = "https://reqres.in/api";

		given()
			.headers("x-api-key","reqres_0d9eae8bc1264c7a871ced5d532a637f")
			.get("/users?page=2")
		.then()
			.assertThat().body(matchesJsonSchemaInClasspath("JSONSchema.json"))
			.statusCode(200)
			.body("data[4].first_name", equalTo("George"))
			.body("data.first_name", hasItems("George","Rachel"))
			.log().all();
	}


}
