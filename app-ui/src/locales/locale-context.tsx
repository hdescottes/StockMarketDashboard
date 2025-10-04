import { createContext, useContext } from "react";

export interface LocaleContextType {
  locale: string;
  setLocale: (lng: string) => void;
}

export const LocaleContext = createContext<LocaleContextType | undefined>(undefined);

export const useLocale = () => {
  const ctx = useContext(LocaleContext);
  if (!ctx) {
    throw new Error("useLocale must be used within a LocaleProvider");
  }
  return ctx;
};
