import { DashboardSearch } from "../components/dashboard-search";
import { useDashboard } from "./use-dashboard.hook";
import Box from "@mui/material/Box";
import { Header } from "../../../components/header";
import { DashboardList } from "../components/dashboard-list";
import { createStock } from "../../../model/stock";
import { useIntl } from "react-intl";

export const Dashboard = () => {
  const translate = useIntl();
  const { stock, setStock, fetch, search, stocks } = useDashboard(createStock());

  return (
    <Box m="20px">
      <Box display="flex" justifyContent="space-between" alignItems="center">
        <Header title={translate.formatMessage({ id: "dashboard.title" })} subtitle={translate.formatMessage({ id: "dashboard.subtitle" })} />
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
