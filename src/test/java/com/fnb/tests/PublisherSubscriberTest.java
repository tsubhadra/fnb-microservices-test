package com.fnb.tests;

import org.junit.jupiter.api.*;
import io.restassured.response.Response;
import utils.HttpClient;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PublisherSubscriberTest {

    static String BASE_URL_PUBLISHER = "http://localhost:8081/publisher";
    static String BASE_URL_SUBSCRIBER = "http://localhost:8082/subscriber";
    static int createdId;

    @Test
    @Order(1)
    void testCreatePublisher() {
        String payload = "{ \"name\": \"Budget Report\", \"status\": \"draft\" }";
        Response response = HttpClient.post(BASE_URL_PUBLISHER, payload);

        assertEquals(201, response.statusCode());
        createdId = response.jsonPath().getInt("id");
        assertNotNull(createdId);
    }

    @Test
    @Order(2)
    void testReadPublisher() {
        Response response = HttpClient.get(BASE_URL_PUBLISHER + "/" + createdId);

        assertEquals(200, response.statusCode());
        assertEquals("Budget Report", response.jsonPath().getString("name"));
    }

    @Test
    @Order(3)
    void testUpdatePublisher() {
        String payload = "{ \"name\": \"Budget Report - Updated\", \"status\": \"approved\" }";
        Response response = HttpClient.put(BASE_URL_PUBLISHER + "/" + createdId, payload);

        assertEquals(200, response.statusCode());
        assertEquals("approved", response.jsonPath().getString("status"));
    }

    @Test
    @Order(4)
    void testDeletePublisher() {
        Response response = HttpClient.delete(BASE_URL_PUBLISHER + "/" + createdId);

        assertEquals(204, response.statusCode());
    }
}
