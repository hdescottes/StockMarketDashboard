Feature: Stock Details Page

Scenario: The user can see a stock details
    When The user visit dashboard search page
    Then The user clicks on the search all button
    Then The user sees the last stock 'MC.XPAR' in the list
    Then The user clicks on the see button for 'MC.XPAR'
    Then The user is redirected to the stock details page for 'MC.XPAR'
    Then The user can see the stock information
