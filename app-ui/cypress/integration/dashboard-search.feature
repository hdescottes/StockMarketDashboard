Feature: Dashboard Search Page

Scenario: The user can fetch a stock
    When The user visit dashboard search page
    Then The user fills the search section with 'AI.XPAR'
    Then The user clicks on the fetch button for 'AI'
    Then The user sees the last stock 'AI.XPAR' in the list
    Then The user fills the search section with 'RMS.XPAR'
    Then The user clicks on the fetch button for 'RMS'
    Then The user sees the last stock 'RMS.XPAR' in the list
    Then The user sees both 'AI.XPAR' and 'RMS.XPAR' in the list
