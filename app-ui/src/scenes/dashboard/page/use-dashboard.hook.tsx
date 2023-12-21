import { useState } from "react";
import { DashboardService } from "../../../services/dashboard.service";
import { Stock } from "../../../model/stock";

function convertToStock(stock: Stock): Stock {
  return {
    id: stock.id,
    date: stock.date,
    symbol: stock.symbol,
    name: stock.name,
    volume: stock.volume,
    open: stock.open,
    close: stock.close,
    high: stock.high,
    low: stock.low,
  };
}

export const useDashboard = (newStock: Stock) => {
  const [stock, setStock] = useState(newStock);
  const [stockList, setStockList] = useState<Stock[]>([]);
  const dashboardService = new DashboardService();

  async function fetch() {
    const updatedStock = await getLastWorkingDayBySymbol();

    if (updatedStock && (!updatedStock.id || updatedStock.id === "")) {
      const stockResponse = await dashboardService.fetch(stock.symbol);
      setStock(stockResponse[0]);
      createAll(stockResponse.map(convertToStock));
    } else {
      search();
    }
  }

  async function getLastWorkingDayBySymbol(): Promise<Stock> {
    return new Promise((resolve) => {
      dashboardService
        .getLastWorkingDayBySymbol(stock.symbol)
        .then((stockResponse) => {
          if (stockResponse) {
            setStock(stockResponse);
            resolve(stockResponse);
          }
        });
    });
  }

  function createAll(createStocks: Stock[]) {
    dashboardService.createAll(createStocks).then(() => {
      search();
    });
  }

  function search() {
    dashboardService.search().then((stocksResponse) => {
      if (stocksResponse) {
        setStockList(stocksResponse);
      }
    });
  }

  return {
    stock,
    setStock,
    fetch,
    search,
    stocks: stockList,
  };
};
