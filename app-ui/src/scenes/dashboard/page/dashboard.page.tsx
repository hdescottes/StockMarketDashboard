import { DashboardSearch } from "../components/dashboard-search";
import { useDashboard } from "./use-dashboard.hook";
import Box from "@mui/material/Box";
import { Header } from "../../../components/header";
import { DashboardDetails } from "../components/dashboard-details";
import { newStock } from "../../../model/stock";

export const Dashboard = () => {
  const { stock, setStock, fetch, search, stocks } = useDashboard(newStock);

  return (
    <Box m="20px">
      <Box display="flex" justifyContent="space-between" alignItems="center">
        <Header title="DASHBOARD" subtitle="Welcome to your dashboard" />
      </Box>
      <DashboardSearch
        stock={stock}
        onChange={setStock}
        fetch={fetch}
        search={search}
      />
      <DashboardDetails stocks={stocks} />
    </Box>
  );
};
