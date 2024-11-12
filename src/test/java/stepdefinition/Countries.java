package stepdefinition;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Countries {

    RequestSpecification httprequest;
    Response response;
    int responseCode;

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

    @Then("I verify the schema")
    public void i_verify_the_schema() {

        String responseBody = response.getBody().asString();

        assertEquals(responseCode, 200);
        assertThat(responseBody, JsonSchemaValidator.matchesJsonSchemaInClasspath("restCountriesSchema.json"));
    }

    @Then("Verify that there are {int} countries")
    public void verify_that_there_are_countries(Integer expectedNumberOfCountries) {

        //JSON representation from response body
        JsonPath jsonPath = response.jsonPath();

        ArrayList<String> listOfCountries = jsonPath.getJsonObject("name.common");

        assertEquals(responseCode, 200);
        assertEquals(expectedNumberOfCountries, listOfCountries.size());
    }

    @When("I pass the url {string} of a specific country in the request")
    public void i_pass_the_url_of_south_africa_in_the_request(String country) {

        httprequest = given().pathParam("country", country);
        response = httprequest.get("v3.1/name/{country}");

        responseCode = response.statusCode();
    }

    @Then("Verify that the abbreviation {string} and language {string} is included")
    public void verify_that_there_sasl_is_included(String expectedLanguageAbbr, String expectedLanguageName) {

        String langFullName = expectedLanguageAbbr+"="+expectedLanguageName;
        assertEquals(responseCode, 200);

        //JSON representation from response body
        JsonPath jsonPath = response.jsonPath();

        ArrayList<LinkedHashMap<String, String>> languageList = new ArrayList<>(jsonPath.getList("languages"));

        LinkedHashMap<String, String> languagesMap = languageList.get(0);

        assertTrue("Expected the map to contain "+langFullName, languagesMap.containsKey(expectedLanguageAbbr) && expectedLanguageName.equals(languagesMap.get(expectedLanguageAbbr)));
    }
}
