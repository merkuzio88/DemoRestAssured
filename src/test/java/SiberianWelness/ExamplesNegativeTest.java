package SiberianWelness;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class ExamplesNegativeTest {

    private final static String URL = "https://ru.siberianhealth.com/ru/";

    @BeforeMethod

    public void setFilter(){

        RestAssured.filters(new AllureRestAssured());
    }

    @Test
    @Description("Check register validate with incorrect method")
    @Severity(SeverityLevel.CRITICAL)
    public void CheckRegisterTest(){
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecError405());
        given()
                .when()
                .get("shop/ajax/registration/data/validate/")
                .then()
                .assertThat()
                .header("Content-Type", "text/html;charset=UTF-8");
            }

    @Test
    @Description("Check login without data")
    @Severity(SeverityLevel.CRITICAL)
    public void CheckLoginTest(){
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecError400());
        given()
                .when()
                .post("shop/login-check/")
                .then()
                .assertThat()
                .header("Content-Type", "text/html; charset=UTF-8");
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
