package SiberianWelness;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class ExamplesPositiveTest {

    private final static String URL = "https://ru.siberianhealth.com/ru/";

    @BeforeMethod

    public void setFilter(){

        RestAssured.filters(new AllureRestAssured());
    }

    @Test
    @Description("Check main page")
    @Severity(SeverityLevel.NORMAL)
    public void CheckMainPageTest(){
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecOK200());
        given()
                .when()
                .get()
                .then()
                .assertThat()
                .header("Content-Type", "text/html; charset=UTF-8");
    }

    @Test
    @Description("Check register validate without data")
    @Severity(SeverityLevel.NORMAL)
    public void CheckRegisterTest(){
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecOK200());
        Response response = given()
                .when()
                .post("shop/ajax/registration/data/validate/")
                .then()
                .assertThat()
                .header ("Content-Type", "application/json")
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        Boolean success = jsonPath.get("success");
        Assertions.assertEquals(false, success);
    }

    @Test
    @Description("Check login with uncorrected data")
    @Severity(SeverityLevel.NORMAL)
    public void CheckLoginTest(){
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecOK200());
        Map<String, String> user = new HashMap<>();
        user.put("_username", "12345678901234567890");
        user.put("_password", "qwertyqwertyqwerty");
        user.put("_gaID", "408734503.1679252938");
        Response response = given()
                .body(user)
                .when()
                .post("shop/login-check/")
                .then()
                .assertThat()
                .header("Content-Type", "application/json")
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        Boolean success = jsonPath.get("success");
        Assertions.assertEquals(false, success);
        System.out.println(jsonPath.getString("message"));

    }

    @AfterMethod
    public void getTestExecutionTime (ITestResult result) {
        String methodName = result.getMethod ()
                .getMethodName ();
        long totalExecutionTime = (result.getEndMillis () - result.getStartMillis ());
        System.out.println (
                "Total Execution time: " + totalExecutionTime + " milliseconds" + " for method " + methodName);
    }


}
