package restAndSoap;

import static io.restassured.RestAssured.get;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.testng.Assert;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import static io.restassured.matcher.RestAssuredMatchers.matchesXsdInClasspath;
import io.restassured.response.Response;



public class SoapRequest {

	//@Test
	void SoapRequest() throws IOException {

		File file = new File("./SoapRequest/Add.xml");
		FileInputStream fileInputStream = new FileInputStream(file);
		String requestBody = IOUtils.toString(fileInputStream, "UTF-8");
		
		
		baseURI = "http://www.dneonline.com";
		
		given().
			contentType(ContentType.XML).
			accept(ContentType.XML).
			body(requestBody).
		when().
			post("/calculator.asmx").
		then().
			assertThat().body(matchesXsdInClasspath("Calculator.xsd")).
			statusCode(200).
			log().all();

	}

}
