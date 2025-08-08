import { When, Then } from "@badeball/cypress-cucumber-preprocessor";
import { StockDetailsPage } from "../pages/stock-details.page";

const stockDetailsPage = new StockDetailsPage();

When("The user visit dashboard search page", () => {
  stockDetailsPage.visitStockDetailsPage();
});

Then("The user clicks on the search all button", () => {
  stockDetailsPage.search();
});

Then("The user sees the last stock {string} in the list", (stock: string) => {
  stockDetailsPage.checkLastStock(stock);
});

Then("The user clicks on the see button for {string}", (stock: string) => {
  stockDetailsPage.clickSeeButton(stock);
});

Then(
  "The user is redirected to the stock details page for {string}",
  (stock: string) => {
    stockDetailsPage.redirectToStockDetails(stock);
  }
);

Then("The user can see the stock information", () => {
  stockDetailsPage.checkStockInformation();
});
