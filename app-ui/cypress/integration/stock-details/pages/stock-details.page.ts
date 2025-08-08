export class StockDetailsPage {
  visitStockDetailsPage() {
    cy.visit("/");
  }

  search() {
    cy.intercept("/api/stocks/latest").as("stock-latest");
    cy.get("#searchall").click();
    cy.wait("@stock-latest");
  }

  checkLastStock(stock: string) {
    cy.contains("div.ag-cell", stock).should("be.visible");
  }

  clickSeeButton(stock: string) {
    cy.intercept("/api/stocks/" + stock).as("get-stock");
    cy.contains("div.ag-cell", stock)
      .should("be.visible")
      .parent("div.ag-row")
      .find(`[col-id="action"]`)
      .within(() => {
        cy.get("#See").click();
      });
    cy.wait("@get-stock");
  }

  redirectToStockDetails(stock: string) {
    cy.url().should("include", "/stocks/" + stock);
  }

  checkStockInformation() {
    cy.get('[data-testid="close"]')
      .should("be.visible")
      .within(() => {
        cy.get("h3").should("not.be.null").contains(606.8);
      });

    cy.get('[data-testid="volume"]')
      .should("be.visible")
      .within(() => {
        cy.get("h5").should("not.be.null").contains(49000);
      });

    cy.get('[class="apexcharts-candlestick-series apexcharts-plot-series"]')
      .should("be.visible")
      .find('[class="apexcharts-series"]')
      .should("exist");

    cy.get('[class="apexcharts-bar-series apexcharts-plot-series"]')
      .should("be.visible")
      .find('[class="apexcharts-series"]')
      .should("exist");
  }
}
