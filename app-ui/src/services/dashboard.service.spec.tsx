import { Stock, createStock } from "../model/stock";
import { DashboardService } from "./dashboard.service";
import { HttpService } from "./http.service";

jest.mock("./http.service");
const httpService = HttpService as jest.MockedClass<typeof HttpService>;
const stock = {} as Stock;

describe("Dashboard service", () => {
  describe("Call to fetch stocks", () => {
    it("Succeeds, should return list of stock", async () => {
      const symbol = "toto";
      const stockResponse: Stock[] = [createStock()];
      const dashboardService = new DashboardService();

      httpService.prototype.get.mockResolvedValue({
        data: stockResponse,
      });

      const response = await dashboardService.fetch(symbol);
      expect(httpService.prototype.get).toHaveBeenCalledWith(
        expect.stringContaining("/v1/eod?access_key=")
      );
      expect(response).toEqual(stockResponse);
    });

    it("Fails, should return empty array", async () => {
      const symbol = "toto";
      const dashboardService = new DashboardService();

      httpService.prototype.get.mockRejectedValue(
        new Error("Failed to fetch data")
      );

      const response = await dashboardService.fetch(symbol);
      expect(httpService.prototype.get).toHaveBeenCalledWith(
        expect.stringContaining("/v1/eod?access_key=")
      );
      expect(response).toEqual([]);
    });
  });
  describe("Call to get latest stock by symbol", () => {
    it("Succeeds, should return a stock", async () => {
      const symbol = "toto";
      const dashboardService = new DashboardService();

      httpService.prototype.get.mockResolvedValue({ response: stock });

      const response = await dashboardService.getLastWorkingDayBySymbol(symbol);
      expect(httpService.prototype.get).toHaveBeenCalledWith(
        `/api/stocks/${symbol}/last-working-day`
      );
      expect(response).toEqual({ response: stock });
    });

    it("Fails, should return empty stock", async () => {
      const symbol = "toto";
      const dashboardService = new DashboardService();

      httpService.prototype.get.mockRejectedValue(
        new Error("Failed to fetch data")
      );

      const response = await dashboardService.getLastWorkingDayBySymbol(symbol);
      expect(httpService.prototype.get).toHaveBeenCalledWith(
        `/api/stocks/${symbol}/last-working-day`
      );
      expect(response).toEqual(createStock());
    });
  });
  describe("Call to search stocks", () => {
    it("Succeeds, should return list of stock", async () => {
      const dashboardService = new DashboardService();

      httpService.prototype.get.mockResolvedValue({ response: [stock, stock] });

      const response = await dashboardService.search();
      expect(httpService.prototype.get).toHaveBeenCalledWith(
        "/api/stocks/latest"
      );
      expect(response).toEqual({ response: [stock, stock] });
    });

    it("Fails, should return empty array", async () => {
      const dashboardService = new DashboardService();

      httpService.prototype.get.mockRejectedValue(
        new Error("Failed to fetch data")
      );

      const response = await dashboardService.search();
      expect(httpService.prototype.get).toHaveBeenCalledWith(
        "/api/stocks/latest"
      );
      expect(response).toEqual([]);
    });
  });

  describe("Call to create stocks", () => {
    it("Succeeds, should create stocks", async () => {
      const dashboardService = new DashboardService();
      const stocks = [createStock(), createStock()];

      httpService.prototype.post.mockResolvedValue({ response: 1 });

      const response = await dashboardService.createAll(stocks);
      expect(httpService.prototype.post).toHaveBeenCalledWith(
        "/api/stocks/all",
        stocks
      );
      expect(response).toEqual({ response: 1 });
    });

    it("Fails, should return null", async () => {
      const dashboardService = new DashboardService();
      const stocks = [createStock(), createStock()];

      httpService.prototype.post.mockRejectedValue(
        new Error("Failed to fetch data")
      );

      const response = await dashboardService.createAll(stocks);
      expect(httpService.prototype.post).toHaveBeenCalledWith(
        "/api/stocks/all",
        stocks
      );
      expect(response).toEqual(null);
    });
  });
});
