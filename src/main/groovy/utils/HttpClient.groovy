package utils

import io.restassured.RestAssured
import io.restassured.response.Response

class HttpClient {
    static Response get(String url) {
        return RestAssured.get(url)
    }

    static Response post(String url, Object body) {
        return RestAssured.given().contentType("application/json").body(body).post(url)
    }

    static Response put(String url, Object body) {
        return RestAssured.given().contentType("application/json").body(body).put(url)
    }

    static Response delete(String url) {
        return RestAssured.delete(url)
    }
}
