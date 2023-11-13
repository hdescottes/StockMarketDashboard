import { When, Then } from "@badeball/cypress-cucumber-preprocessor";

When("The user visit home page", () => {
  cy.visit("/");
});

Then("The user see the title", () => {
  cy.get("h5").contains("Welcome to your dashboard");
});
