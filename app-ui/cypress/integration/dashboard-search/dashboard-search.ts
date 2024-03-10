import { When, Then } from "@badeball/cypress-cucumber-preprocessor";

const marketStackApi = "/v1/eod?access_key=*";

When("The user visit dashboard search page", () => {
  cy.visit("/");
});

Then("The user fills the search section with {string}", (stock: string) => {
  cy.get('input[name="symbol"]').should("be.visible").type(stock);
  cy.get('input[name="symbol"]').should("not.be.null");
});

Then("The user clicks on the fetch button for {string}", (stock: string) => {
  cy.fixture("data-" + stock + ".json").then((mockData) => {
    cy.intercept(marketStackApi, {
      statusCode: 200,
      body: mockData,
    }).as("marketStackApi");
  });

  cy.intercept("/api/stocks/latest").as("stock-latest");
  cy.get("#fetch").click();
  cy.wait("@stock-latest");
});

Then("The user sees the last stock {string} in the list", (stock: string) => {
  cy.contains("div.ag-cell", stock).should("be.visible");
});

Then(
  "The user sees both {string} and {string} in the list",
  (stock1: string, stock2: string) => {
    const stocks = [stock1, stock2];
    stocks.forEach((stock) => {
      cy.contains("div.ag-cell", stock).should("be.visible");
    });
  }
);
