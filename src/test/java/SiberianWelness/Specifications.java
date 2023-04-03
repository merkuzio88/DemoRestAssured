package SiberianWelness;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static org.hamcrest.Matchers.lessThan;

public class Specifications {
    public static RequestSpecification requestSpec(String url){
        return new RequestSpecBuilder()
                .setBaseUri(url)
                .build();
    }

    public static ResponseSpecification responseSpecOK200(){
        return new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectHeader("Content-Encoding", "gzip")
                .expectHeader("Connection", "keep-alive")
                .expectHeader("Access-Control-Allow-Origin", "*")
                .expectResponseTime(lessThan(5000L))
                .build();
    }

    public static ResponseSpecification responseSpecError400(){
        return new ResponseSpecBuilder()
                .expectStatusCode(400)
                .expectHeader("Connection", "keep-alive")
                .expectResponseTime(lessThan(5000L))
                .build();
    }

    public static ResponseSpecification responseSpecError405(){
        return new ResponseSpecBuilder()
                .expectStatusCode(405)
                .expectHeader("Connection", "keep-alive")
                .expectResponseTime(lessThan(5000L))
                .build();
    }

    public static void installSpecification(RequestSpecification request, ResponseSpecification response){
        RestAssured.requestSpecification = request;
        RestAssured.responseSpecification = response;
    }
}
