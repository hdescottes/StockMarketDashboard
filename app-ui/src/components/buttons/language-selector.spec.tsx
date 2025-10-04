import { render, screen, fireEvent } from "@testing-library/react";
import { LanguageSelector } from "./language-selector";
import { LocaleContext, LocaleContextType } from "../../locales/locale-context";
import { IntlProvider } from "react-intl";

const renderWithProviders = (locale: "en" | "fr", setLocale = jest.fn()) => {
  const messages = {
    en: { "sidebar.language": "Language" },
    fr: { "sidebar.language": "Langue" },
  };
  const contextValue: LocaleContextType = { locale, setLocale };
  return render(
    <LocaleContext.Provider value={contextValue}>
      <IntlProvider locale={locale} messages={messages[locale]}>
        <LanguageSelector />
      </IntlProvider>
    </LocaleContext.Provider>
  );
};

describe("LanguageSelector", () => {
  it("renders the language selector button", () => {
    renderWithProviders("en");
    expect(screen.getByRole("button")).toBeInTheDocument();
  });

  it("shows the correct flag and label for English", () => {
    renderWithProviders("en");
    expect(screen.getByRole("button").querySelector(".fi-sh")).toBeInTheDocument();
  });

  it("shows the correct flag and label for French", () => {
    renderWithProviders("fr");
    expect(screen.getByRole("button").querySelector(".fi-fr")).toBeInTheDocument();
  });

  it("opens the dropdown and calls setLocale when a language is selected", () => {
    const setLocale = jest.fn();
    renderWithProviders("en", setLocale);

    fireEvent.click(screen.getByRole("button"));

    const frenchOption = screen.getByText("Français");
    fireEvent.mouseDown(frenchOption);
    fireEvent.click(frenchOption);

    expect(setLocale).toHaveBeenCalledWith("fr");
  });

  it("closes the dropdown when backdrop is clicked", () => {
    renderWithProviders("en");
    fireEvent.click(screen.getByRole("button"));
    expect(screen.getByRole("listbox")).toBeInTheDocument();

    fireEvent.click(screen.getByTestId("language-selector-backdrop"));
    expect(screen.queryByRole("listbox")).not.toBeInTheDocument();
  });
});
