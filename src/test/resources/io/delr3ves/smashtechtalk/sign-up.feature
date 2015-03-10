@website
@recorded
Feature: Test the Atrapalo's sign up
  In order to test make a didactic example will test a sample website i.e. sonatalocal.com!!

  Scenario Outline: User goes to atrapalo.com and tries to create an account with valid data so it should be logged into the system.
    Given user is in registration page
    When user creates an user with "<UserData>"
    Then the user should be created

  Examples:
    | UserData   |
    | valid-user |


  Scenario Outline: User goes to atrapalo.com and tries to create an account with invalid data so system will notify about the error.
    Given user is in registration page
    When user creates an user with "<UserData>"
    Then the user will not be created due to <NumberOfErrors> errors are found
  Examples:
    | UserData                   | NumberOfErrors |
    | user-with-invalid-password | 1              |
    | empty-user                 | 6              |