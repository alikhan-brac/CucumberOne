Feature: User login

  Scenario: User can login using valid credential
    Given User navigates to login page
    When User provides valid user id
    And User provides valid password
    And Clicks on login button
    Then User should see login success message
    And Close the browser