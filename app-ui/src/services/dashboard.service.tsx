import { Stock, newStock } from "../model/stock";
import { StockResponse } from "../model/stockResponse";
import { HttpService } from "./http.service";
import token from "../token.json";

export class DashboardService {
  httpService: HttpService = new HttpService();
  tokenValue: string = token.TOKEN;

  fetch(symbol: string): Promise<Stock> {
    /*const today = new Date();
    const to =
      today.getFullYear() + "-" + today.getMonth() + "-" + today.getDay();
    const from =
      today.getFullYear() + "-" + (today.getMonth() - 4) + "-" + today.getDay();*/
    return this.httpService
      .get<StockResponse>(
        `/v1/eod?access_key=${this.tokenValue}&symbols=${symbol}`
      )
      .then(
        (response: StockResponse) => response.data[0],
        (_error) => newStock
      );
  }

  getLastWorkingDayBySymbol(symbol: string): Promise<Stock> {
    return this.httpService
      .get<Stock>(`/api/stocks/${symbol}/last-working-day`)
      .then(
        (response: Stock) => response,
        (_error) => newStock
      );
  }

  search(): Promise<Stock[]> {
    return this.httpService.get<Stock[]>("/api/stocks").then(
      (response: Stock[]) => response,
      (_error) => []
    );
  }

  create(stock: Stock): Promise<number | null> {
    return this.httpService
      .post<number>("/api/stocks", {
        ...stock,
      })
      .then(
        (response: number) => response,
        (_error) => null
      );
  }
}
