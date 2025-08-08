import { When, Then } from "@badeball/cypress-cucumber-preprocessor";
import { DashboardSearchPage } from "../pages/dashboard-search.page";

const dashboardSearchPage = new DashboardSearchPage();

When("The user visit dashboard search page", () => {
  dashboardSearchPage.visitDashboardSearchPage();
});

Then("The user fills the search section with {string}", (stock: string) => {
  dashboardSearchPage.fillSearchSection(stock);
});

Then("The user clicks on the fetch button for {string}", (stock: string) => {
  dashboardSearchPage.clickFetchButton(stock);
});

Then("The user sees the last stock {string} in the list", (stock: string) => {
  dashboardSearchPage.checkLastStock(stock);
});

Then(
  "The user sees both {string} and {string} in the list",
  (stock1: string, stock2: string) => {
    dashboardSearchPage.seeStocks(stock1, stock2);
  }
);
