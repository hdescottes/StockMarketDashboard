import { When, Then } from "@badeball/cypress-cucumber-preprocessor";

When("The user visit dashboard search page", () => {
  cy.visit("/");
});

Then("The user clicks on the search all button", (stock: string) => {
  cy.intercept("/api/stocks/latest").as("stock-latest");
  cy.get("#searchall").click();
  cy.wait("@stock-latest");
});

Then("The user sees the last stock {string} in the list", (stock: string) => {
  cy.contains("div.ag-cell", stock).should("be.visible");
});

Then("The user clicks on the see button for {string}", (stock: string) => {
  cy.intercept("/api/stocks/" + stock).as("get-stock");
  cy.contains("div.ag-cell", stock)
    .should("be.visible")
    .parent("div.ag-row")
    .find(`[col-id="action"]`)
    .within(() => {
      cy.get("#See").click();
    });
  cy.wait("@get-stock");
});

Then(
  "The user is redirected to the stock details page for {string}",
  (stock: string) => {
    cy.url().should("include", "/stocks/" + stock);
  }
);

Then("The user can see the stock information", () => {
  cy.get('[data-testid="close"]')
    .should("be.visible")
    .within(() => {
      cy.get("h3").should("not.be.null");
    });

  cy.get('[data-testid="volume"]')
    .should("be.visible")
    .within(() => {
      cy.get("h5").should("not.be.null");
    });

  cy.get('[class="apexcharts-candlestick-series apexcharts-plot-series"]')
    .should("be.visible")
    .find('[class="apexcharts-series"]')
    .should("exist");

  cy.get('[class="apexcharts-bar-series apexcharts-plot-series"]')
    .should("be.visible")
    .find('[class="apexcharts-series"]')
    .should("exist");
});
