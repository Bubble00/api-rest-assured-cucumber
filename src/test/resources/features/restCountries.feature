
  Feature: Countries from rest countries api

    Scenario: Ensure correct API endpoint schema
      Given I hit the url get rest countries api endpoint
      When I pass url of all countries in the request
      Then I verify the status code as 200

    Scenario: Confirmation of Countries
      Given I hit the url get rest countries api endpoint
      When I pass url of all countries in the request
      Then Verify that there are 195 countries

    Scenario Outline: Validate Languages
      Given I hit the url get rest countries api endpoint
      When I pass the url <specific country> of a specific country in the request
      Then Verify that there <language> is included
      Examples:
      |specific country| language|
      |south africa    | sasl   |