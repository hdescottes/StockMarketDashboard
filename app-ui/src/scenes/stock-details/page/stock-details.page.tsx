import Box from "@mui/material/Box";
import { Header } from "../../../components/header";
import { useStockDetails } from "./use-stock-details.hook";
import { useParams } from "react-router-dom";
import { StockInfo } from "../components/stock-info";
import { StockChart } from "../components/stock-chart";

export const StockDetails = () => {
  const params = useParams();
  const { stocks } = useStockDetails(params.symbol ?? "");
  return (
    <Box m="20px">
      <Box display="flex" justifyContent="space-between" alignItems="center">
        <Header title="STOCK DETAILS" subtitle="Details of the current stock" />
      </Box>
      <StockInfo stock={stocks[0]} stockDayBefore={stocks[1]} />
      <StockChart stocks={stocks} />
    </Box>
  );
};
