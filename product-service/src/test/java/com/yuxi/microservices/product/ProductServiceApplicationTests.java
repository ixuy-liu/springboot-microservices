package com.yuxi.microservices.product;

import io.restassured.RestAssured;
import static org.hamcrest.Matchers.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Import;
import org.testcontainers.containers.MongoDBContainer;

@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

class ProductServiceApplicationTests {

    @ServiceConnection
    static MongoDBContainer mongoDBContainer =  new MongoDBContainer("mongo:latest");
    @LocalServerPort
    private Integer port;


    @BeforeEach
    void setup(){
        RestAssured.baseURI = "http://localhost";
        RestAssured.port  = port;
    }

    static {
        mongoDBContainer.start();
    }

    @Test
    void shouldCreateProduct() {
        String requestBody = """
				{
				     "name": "iphone 15",
				     "description": "iphone 15",
				     "price": 1000
				 }
				""";

        RestAssured.given()
                .contentType("application/json")
                .body(requestBody)
                .when()
                .post("/api/product")
                .then()
                .statusCode(201)
                .body("id", notNullValue())
                .body("name", equalTo("iphone 15"))
                .body("description", equalTo("iphone 15"))
                .body("price", equalTo(1000));

    }

}