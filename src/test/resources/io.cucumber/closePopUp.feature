Feature: MainPage

  Background:
    Given User opens start page
    And https://app.fitogram.pro/signin is opened
    Then User closes pop-up

  Scenario: Empty login and password field
    Given User enters nothing as login
    Then User enters nothing as password
    Then User clicks Sign In button
    And https://app.fitogram.pro/signin is opened
    And Invalid username and/or password is displayed


  Scenario: Empty login and non-empty password field
    Given User enters nothing as login
    Then User enters password as password
    Then User clicks Sign In button
    And https://app.fitogram.pro/signin is opened
    And Invalid username and/or password is displayed

  Scenario: Non-empty login and empty password field
    Given User enters login as login
    Then User enters nothing as password
    Then User clicks Sign In button
    And https://app.fitogram.pro/signin is opened
    And Invalid username and/or password is displayed

  Scenario: Login and password are incorrect
    Given User enters login as login
    Then User enters password as password
    Then User clicks Sign In button
    And https://app.fitogram.pro/signin is opened
    And Invalid username and/or password is displayed

  Scenario: Login and password are correct
    Given User enters v4leri.gurov@yandex.ru as login
    Then User enters AutoTest as password
    Then User clicks Sign In button
    And https://app.fitogram.pro/random/dashboard/checklist is opened
