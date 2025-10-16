import { IntlProvider } from "react-intl";
import enMessages from "../locales/en.json";

export function IntlWrapper({ children }: { children: React.ReactNode }) {
  return (
    <IntlProvider locale="en" messages={enMessages}>
        {children}
    </IntlProvider>
  );
}