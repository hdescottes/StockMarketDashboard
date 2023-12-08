import { useState } from "react";
import { DashboardService } from "../../../services/dashboard.service";
import { Stock } from "../../../model/stock";

export const useDashboard = (newStock: Stock) => {
  const [stock, setStock] = useState(newStock);
  const [stocks, setStocks] = useState<Stock[]>([]);
  const dashboardService = new DashboardService();

  async function fetch() {
    const updatedStock = await getById();

    if (updatedStock && (!updatedStock.id || updatedStock.id === "")) {
      const stockResponse = await dashboardService.fetch(stock.symbol);
      setStock(stockResponse);
      create(stockResponse);
    } else {
      search();
    }
  }

  async function getById(): Promise<Stock> {
    return new Promise((resolve) => {
      const id = stock.symbol + new Date().toISOString().split("T")[0];

      dashboardService.getById(id).then((stockResponse) => {
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
    stocks,
  };
};
