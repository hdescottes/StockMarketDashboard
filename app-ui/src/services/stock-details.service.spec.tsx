import { Stock } from "../model/stock";
import { HttpService } from "./http.service";
import { StockDetailsService } from "./stock-details.service";

jest.mock("./http.service");
const httpService = HttpService as jest.MockedClass<typeof HttpService>;
const stock = {} as Stock;

describe("Stock details service", () => {
  describe("Call to get stocks by symbol", () => {
    it("Succeeds, should return list of stock", async () => {
      const symbol = "toto";
      const stockDetailsService = new StockDetailsService();

      httpService.prototype.get.mockResolvedValue({ response: [stock, stock] });

      const response = await stockDetailsService.getBySymbol(symbol);
      expect(httpService.prototype.get).toHaveBeenCalledWith(
        `/api/stocks/${symbol}`
      );
      expect(response).toEqual({ response: [stock, stock] });
    });

    it("Fails, should return empty array", async () => {
      const symbol = "toto";
      const stockDetailsService = new StockDetailsService();

      httpService.prototype.get.mockRejectedValue(
        new Error("Failed to fetch data")
      );

      const response = await stockDetailsService.getBySymbol(symbol);
      expect(httpService.prototype.get).toHaveBeenCalledWith(
        `/api/stocks/${symbol}`
      );
      expect(response).toEqual([]);
    });
  });
});
