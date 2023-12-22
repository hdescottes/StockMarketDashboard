import { DashboardSearch } from "../components/dashboard-search";
import { useDashboard } from "./use-dashboard.hook";
import Box from "@mui/material/Box";
import { Header } from "../../../components/header";
import { DashboardList } from "../components/dashboard-list";
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
      <DashboardList stocks={stocks} />
    </Box>
  );
};
