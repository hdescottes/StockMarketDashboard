import { ColorModeContext, useMode } from "./theme";
import { ThemeProvider } from "@emotion/react";
import { CssBaseline } from "@mui/material";
import { Routes, Route } from "react-router-dom";
import { TopBar } from "./components/global/top-bar";
import { Dashboard } from "./scenes/dashboard/page/dashboard.page";
import { SideBar } from "./components/global/side-bar";
import { StockDetails } from "./scenes/stock-details/page/stock-details.page";

function App() {
  const [theme, colorMode] = useMode();

  return (
    <ColorModeContext value={colorMode}>
      <ThemeProvider theme={theme}>
        <CssBaseline />
        <div className="app">
          <SideBar />
          <main className="content">
            <TopBar />
            <Routes>
              <Route path="/" element={<Dashboard />} />
              <Route path="/stocks/:symbol" element={<StockDetails />} />
            </Routes>
          </main>
        </div>
      </ThemeProvider>
    </ColorModeContext>
  );
}

export default App;
