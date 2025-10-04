import React, { useState, useMemo } from "react";
import ReactDOM from "react-dom/client";
import App from "./App";
import { LocaleContext } from "./locales/locale-context";
import { BrowserRouter } from "react-router-dom";
import { IntlProvider } from "react-intl";
import enMessages from "./locales/en.json";
import frMessages from "./locales/fr.json";
import "./index.scss";
import 'flag-icons/css/flag-icons.min.css';

const messages: Record<string, Record<string, string>> = {
  en: enMessages,
  fr: frMessages,
};

const getInitialLocale = () => {
  const saved = localStorage.getItem("locale");
  if (saved && messages[saved]) {
    return saved;
  }
  return navigator.language.startsWith("fr") ? "fr" : "en";
};

const Root = () => {
  const [locale, setLocale] = useState(getInitialLocale());
  const value = useMemo(() => ({
    locale,
    setLocale: (lng: string) => {
      localStorage.setItem("locale", lng);
      setLocale(lng);
    },
  }), [locale]);

  return (
    <React.StrictMode>
      <BrowserRouter>
        <LocaleContext value={value}>
          <IntlProvider locale={locale} messages={messages[locale]}>
            <App />
          </IntlProvider>
        </LocaleContext>
      </BrowserRouter>
    </React.StrictMode>
  );
};

const root = ReactDOM.createRoot(
  document.getElementById("root") as HTMLElement
);
root.render(<Root />);
