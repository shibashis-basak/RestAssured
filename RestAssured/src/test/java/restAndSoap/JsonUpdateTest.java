package restAndSoap;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import java.io.File;

public class JsonUpdateTest {

    public static void main(String[] args) throws Exception {

        ObjectMapper mapper = new ObjectMapper();

        // 1. Read JSON file
        JsonNode rootNode = mapper.readTree(
                new File("src/test/resources/request.json")
        );

        // 2. Update top-level/nested fields

        // customer.name
        ((ObjectNode) rootNode.get("customer"))
                .put("name", "Rahul");

        // customer.contact.email
        ((ObjectNode) rootNode
                .get("customer")
                .get("contact"))
                .put("email", "rahul@test.com");

        // customer.contact.phone
        ((ObjectNode) rootNode
                .get("customer")
                .get("contact"))
                .put("phone", "9876543210");

        // order.orderId
        ((ObjectNode) rootNode.get("order"))
                .put("orderId", "ORD999");

        // order.payment.method
        ((ObjectNode) rootNode
                .get("order")
                .get("payment"))
                .put("method", "UPI");

        // order.payment.status
        ((ObjectNode) rootNode
                .get("order")
                .get("payment"))
                .put("status", "SUCCESS");

        // 3. Update array values
        JsonNode items = rootNode.get("order").get("items");

        // First item quantity
        ((ObjectNode) items.get(0))
                .put("quantity", 5);

        // Second item price
        ((ObjectNode) items.get(1))
                .put("price", 1500);

        // 4. Convert modified JSON to String
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
                .post("baseurl/orders")
                .then()
                .statusCode(200);
    }
}