import { Stock } from "../model/stock";
import { HttpService } from "./http.service";

export class StockDetailsService {
  httpService: HttpService = new HttpService();

  getBySymbol(symbol: string): Promise<Stock[]> {
    return this.httpService.get<Stock[]>(`/api/stocks/${symbol}`).then(
      (response: Stock[]) => response,
      (_error) => []
    );
  }
}
