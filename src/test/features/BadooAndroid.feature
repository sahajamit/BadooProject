Feature: Sign-up Functionality

  Scenario Outline: Sign-up via Email

    Given a new user with photo
    When I go to Nearby screen
    And  I block a user from People Nearby
    And  I go to settings page
    And  I tap on Blocked Users
    Then I should see blocked users
    When I go back to my profile
    And  I go to Nearby screen
    And  I wait for the blocked user in People Nearby
    And  I open the blocked profile from People Nearby
    And  I unblock the user
    And  I go back to People Nearby screen
    And  I go to my profile
    And  I go to settings page
    Then I should not see Blocked Users option
    Examples:
      |  |





