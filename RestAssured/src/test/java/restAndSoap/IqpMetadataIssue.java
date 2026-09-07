package restAndSoap;

import java.io.File;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class IqpMetadataIssue {

	public static void main(String[] args) throws Exception {

        ObjectMapper mapper = new ObjectMapper();

        // Read JSON file
        JsonNode rootNode = mapper.readTree(
                new File("src/test/resources/IqpMetadataRequest.json")
        );

        // Update top-level/nested fields

        // file name
        ((ObjectNode) rootNode)
                .put("fileName", "XYZ");

        // Update array values
        JsonNode entityAttribute = rootNode.get("entityAttribute");

        // Update First entityAttribute
        ((ObjectNode) entityAttribute.get(0))
                .put("mappingAttributeName", "XYZ");

        // Update Second entityAttribute
        ((ObjectNode) entityAttribute.get(0))
                .put("name", "XYZ");

        // Convert modified JSON to String
        String requestBody = mapper
                .writerWithDefaultPrettyPrinter()
                .writeValueAsString(rootNode);

        System.out.println(requestBody);

        // 5. Send through REST Assured
        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("baseurl/endpoint")
                .then()
                .statusCode(200);
    }
	
}
