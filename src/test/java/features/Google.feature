Feature: Google search smoke scenario

  Scenario: Test search
    Given Open chrome browser and open google page
    When I enter "IBM" search request
    Then Search results should contain "IBM"
    Then Browser should be closed