Feature: Story1

  Background:
    Given I Launch the browser
    When I open the URL "https://currency-converter-six-tan.vercel.app"

  Scenario: Core Flip Functionality
    Then I Navigate to the About us page
    Then I identify the team card and performe a mouse hover
    Then I Checked if the Member Quote (reverse side) becomes visible
    

  Scenario: Verify Dark/Light Mode Theme Adaptation
    Then I Chanced the theam to dark mode
    Then I checked Theam should be changed
   

  Scenario: Verify system resilience against character limits and missing data (Negative/Edge Case).
    Then I entered the amount in the input filed
    Then I click on convert button
    Then I checked for the Numeric amount as result
