
  Feature: Countries from rest countries api

    Scenario: Ensure correct API endpoint schema
      Given I hit the url get rest countries api endpoint
      When I pass url of all countries in the request
      Then I verify the schema

    Scenario: Confirmation of Countries
      Given I hit the url get rest countries api endpoint
      When I pass url of all countries in the request
      Then Verify that there are 195 countries

    Scenario Outline: Validate Languages
      Given I hit the url get rest countries api endpoint
      When I pass the url "<specific country>" of a specific country in the request
      Then Verify that the abbreviation "<abr_language>" and language "<full_name_language>" is included
      Examples:
      |specific country| abr_language|full_name_language|
      |south africa    | sasl         |South African Sign Language|
