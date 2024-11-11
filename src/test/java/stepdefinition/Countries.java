package stepdefinition;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Countries {

    RequestSpecification httprequest;
    Response response;
    int responseCode;
    ResponseBody body;

    @Given("I hit the url get rest countries api endpoint")
    public void i_hit_the_url_get_rest_countries_api_endpoint() {
        RestAssured.baseURI = "https://restcountries.com/";
    }

    @When("I pass url of all countries in the request")
    public void i_pass_the_url_of_all_countries_in_the_request() {
        httprequest = given().log().all();
        response = httprequest.get("/v3.1/all/");

        responseCode = response.statusCode();
    }

    @Then("I verify the status code as {int}")
    public void i_verify_the_status_code_as(int expectedStatusCode) {
//        responseCode = response.statusCode();

        String responseBody = response.getBody().asString();

        assertEquals(responseCode, expectedStatusCode);
        assertThat(responseBody, JsonSchemaValidator.matchesJsonSchemaInClasspath("restCountriesSchema.json"));
//        assertEquals(responseCode, expectedStatusCode);
    }

    @Then("Verify that there are {int} countries")
    public void verify_that_there_are_countries(Integer expectedNumberOfCountries) {
        body = response.getBody();

        //JSON representation from response body
        JsonPath jsonPath = response.jsonPath();

        String numberOfCountries = jsonPath.getJsonObject("name.common").toString();
        System.out.println( numberOfCountries.length());
        String[] allCountries = (numberOfCountries.split(","));
//        System.out.println(allCountries[0]);
//        System.out.println(allCountries.length);
        assertEquals(responseCode, 200);
        assertEquals(expectedNumberOfCountries, allCountries.length);
    }

    @When("I pass the url {} of a specific country in the request")
    public void i_pass_the_url_of_south_africa_in_the_request(String country) {
        httprequest = given().pathParam("country", country);
        response = httprequest.get("v3.1/name/{country}");

        responseCode = response.statusCode();
    }

    @Then("Verify that there {} is included")
    public void verify_that_there_sasl_is_included(String expectedLanguage) {
        //JSON representation from response body
        JsonPath jsonPath = response.jsonPath();

        assertEquals(responseCode, 200);
//        ArrayList<String> sl = jsonPath.getJsonObject("languages");
        assertThat(jsonPath.getJsonObject("languages"), hasItem(expectedLanguage));

    }
}
