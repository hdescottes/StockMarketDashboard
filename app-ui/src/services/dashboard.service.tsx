import { Stock, newStock } from "../model/stock";
import { StockResponse } from "../model/stockResponse";
import { HttpService } from "./http.service";
import token from "../token.json";

export class DashboardService {
  httpService: HttpService = new HttpService();
  tokenValue: string = token.TOKEN;

  fetch(symbol: string): Promise<Stock[]> {
    const today = new Date();
    let year = today.getUTCFullYear();
    let month = today.getUTCMonth() - 3;
    if (month < 0) {
      year -= 1;
      month += 12;
    }

    const to =
      today.getUTCFullYear().toString() +
      "-" +
      ("0" + (today.getUTCMonth() + 1).toString()).slice(-2) +
      "-" +
      ("0" + today.getUTCDate().toString()).slice(-2);
    const from =
      year.toString() +
      "-" +
      ("0" + month.toString()).slice(-2) +
      "-" +
      ("0" + today.getUTCDate().toString()).slice(-2);
    return this.httpService
      .get<StockResponse>(
        `/v1/eod?access_key=${this.tokenValue}&symbols=${symbol}&date_from=${from}&date_to=${to}`
      )
      .then(
        (response: StockResponse) => response.data,
        (_error) => []
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
    return this.httpService.get<Stock[]>("/api/stocks/latest").then(
      (response: Stock[]) => response,
      (_error) => []
    );
  }

  createAll(stocks: Stock[]): Promise<number | null> {
    return this.httpService.post<number>("/api/stocks/all", stocks).then(
      (response: number) => response,
      (_error) => null
    );
  }
}
