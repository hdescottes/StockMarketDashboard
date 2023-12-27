import { useEffect, useState } from "react";
import { StockDetailsService } from "../../../services/stock-details.service";
import { Stock } from "../../../model/stock";

export const useStockDetails = (symbol: string) => {
  const [stocks, setStocks] = useState<Stock[]>([]);
  const stockDetailsService = new StockDetailsService();

  useEffect(() => {
    getBySymbol(symbol);
    // eslint-disable-next-line
  }, [symbol]);

  const getBySymbol = (symbol: string) => {
    stockDetailsService.getBySymbol(symbol).then((response) => {
      setStocks(response);
    });
  };

  return {
    stocks,
  };
};
