import React, { useState } from "react";
import { useLocale } from "../../locales/locale-context";
import "./language-selector.scss";
import { useTheme } from "@mui/material";
import { tokens } from "../../theme";

const LANGUAGES = [
  { code: "en", label: "English", flag: "sh" },
  { code: "fr", label: "Français", flag: "fr" },
];

export const LanguageSelector: React.FC = () => {
  const localeContext = useLocale();
  const [open, setOpen] = useState(false);
  const theme = useTheme();
  const colors = tokens(theme.palette.mode);

  const currentLang = LANGUAGES.find(l => l.code === localeContext.locale) || LANGUAGES[0];

  return (
    <div
      className="language-selector"
      style={{
        "--mui-palette-background-paper": colors.white[900],
        "--mui-palette-action-selected": colors.primary[400],
      } as React.CSSProperties}
    >
      <button
        type="button"
        onClick={() => setOpen(o => !o)}
        className={`language-selector__button${open ? " language-selector__button--active" : ""}`}
        aria-haspopup="listbox"
        aria-expanded={open}
      >
        <span className={`fi fi-${currentLang.flag} language-selector__flag`} />
      </button>
      {open && (
        <ul
          tabIndex={-1}
          role="listbox"
          className="language-selector__dropdown"
        >
          {LANGUAGES.map(lang => (
            <li
              key={lang.code}
              role="option"
              aria-selected={localeContext.locale === lang.code}
              onClick={() => {
                localeContext.setLocale(lang.code);
                setOpen(false);
              }}
              className="language-selector__option"
              onMouseDown={e => e.preventDefault()}
            >
              <span className={`fi fi-${lang.flag} language-selector__flag`} />
              {lang.label}
            </li>
          ))}
        </ul>
      )}
      {open && (
        <div
          className="language-selector__backdrop"
          data-testid="language-selector-backdrop"
          onClick={() => setOpen(false)}
        />
      )}
    </div>
  );
};
