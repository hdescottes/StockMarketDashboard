import { DashboardSearch } from "../components/dashboard-search";
import { useDashboard } from "./use-dashboard.hook";
import { newModel } from "../../../model/model";
import Box from "@mui/material/Box";
import { Header } from "../../../components/header";
import { DashboardDetails } from "../components/dashboard-details";

export const Dashboard = () => {
  const { model, setModel, create, models } = useDashboard(newModel);

  return (
    <Box m="20px">
      <Box display="flex" justifyContent="space-between" alignItems="center">
        <Header title="DASHBOARD" subtitle="Welcome to your dashboard" />
      </Box>
      <DashboardSearch model={model} onChange={setModel} create={create} />
      <DashboardDetails models={models} />
    </Box>
  );
};
