const marketStackApi = "/v1/eod?access_key=*";

export class DashboardSearchPage {
  visitDashboardSearchPage() {
    cy.visit("/");
  }

  fillSearchSection(stock: string) {
    cy.get('input[name="symbol"]').should("be.visible").type(stock);
    cy.get('input[name="symbol"]').should("not.be.null");
  }

  clickFetchButton(stock: string) {
    cy.fixture("data-" + stock + ".json").then((mockData) => {
      cy.intercept(marketStackApi, {
        statusCode: 200,
        body: mockData,
      }).as("marketStackApi");
    });

    cy.intercept("/api/stocks/latest").as("stock-latest");
    cy.get("#fetch").click();
    cy.wait("@stock-latest");
  }

  checkLastStock(stock: string) {
    cy.contains("div.ag-cell", stock).should("be.visible");
  }

  seeStocks(stock1: string, stock2: string) {
    const stocks = [stock1, stock2];
    stocks.forEach((stock) => {
      cy.contains("div.ag-cell", stock).should("be.visible");
    });
  }
}
