import { useState } from "react";
import { DashboardService } from "../../../services/dashboard.service";
import { Stock } from "../../../model/stock";

export const useDashboard = (newStock: Stock) => {
  const [stock, setStock] = useState(newStock);
  const [stocks, setStocks] = useState<Stock[]>([]);
  const dashboardService = new DashboardService();

  async function fetch() {
    const updatedStock = await getLastWorkingDayBySymbol();

    if (updatedStock && (!updatedStock.id || updatedStock.id === "")) {
      const stockResponse = await dashboardService.fetch(stock.symbol);
      setStock(stockResponse);
      create(stockResponse);
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

  function create(create: Stock) {
    dashboardService.create(create).then(() => {
      search();
    });
  }

  function search() {
    dashboardService.search().then((stocksResponse) => {
      if (stocksResponse) {
        setStocks(stocksResponse);
      }
    });
  }

  return {
    stock,
    setStock,
    fetch,
    search,
    stocks,
  };
};
