Feature: Story2

  Background:
    Given I Launch the browser
    When I open the URL "https://currency-converter-six-tan.vercel.app"

  Scenario: Verify seamless page switching between Converter, Trends, and About sections.
    Then I Switch to Trends
    Then I Switch to About
    Then I Switch back to Converter
    Then I checked Converter page should display
   

  Scenario Outline: : Verify Input Field Continuity (Selected values are not cleared on navigation).
    Then I Entered the amount "<Amount>"
    Then I Switch to Trends
    Then I Switch to About
    Then I Switch back to Converter
    Then I Verify amount is still there "<Amount>"
   

    Examples:
      | Amount |
      | 750.50 |

  Scenario: Verify that the "Recent Conversions" list data persists after navigating away and returning.
    Then Perform a dummy conversion to generate history
    Then I Ensure history item is present
    Then I Switch to Trends
    Then I Switch to About
    Then I Verify history still exists

  Scenario: Verify that the Global Theme (Dark/Light) state persists during internal navigation.
    Then I Chanced the theam to dark mode
    Then I Switch to Trends
    Then I Switch to About
    Then Verify Light Mode is still active
