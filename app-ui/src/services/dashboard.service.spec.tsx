import { Stock, newStock } from "../model/stock";
import { DashboardService } from "./dashboard.service";
import { HttpService } from "./http.service";

jest.mock("./http.service");
const httpService = HttpService as jest.MockedClass<typeof HttpService>;
const stock = {} as Stock;

describe("Dashboard service", () => {
  describe("Call to get stock by id", () => {
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

      httpService.prototype.get.mockResolvedValue({ response: newStock });

      const response = await dashboardService.getLastWorkingDayBySymbol(symbol);
      expect(httpService.prototype.get).toHaveBeenCalledWith(
        `/api/stocks/${symbol}/last-working-day`
      );
      expect(response).toEqual({ response: newStock });
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

      httpService.prototype.get.mockResolvedValue({ response: [] });

      const response = await dashboardService.search();
      expect(httpService.prototype.get).toHaveBeenCalledWith(
        "/api/stocks/latest"
      );
      expect(response).toEqual({ response: [] });
    });
  });

  describe("Call to create stock", () => {
    it("Succeeds, should create a stock", async () => {
      const dashboardService = new DashboardService();

      httpService.prototype.post.mockResolvedValue({ response: 1 });

      const response = await dashboardService.create(newStock);
      expect(httpService.prototype.post).toHaveBeenCalledWith(
        "/api/stocks",
        newStock
      );
      expect(response).toEqual({ response: 1 });
    });

    it("Fails, should return null", async () => {
      const dashboardService = new DashboardService();

      httpService.prototype.post.mockResolvedValue({ response: null });

      const response = await dashboardService.create(newStock);
      expect(httpService.prototype.post).toHaveBeenCalledWith(
        "/api/stocks",
        newStock
      );
      expect(response).toEqual({ response: null });
    });
  });
});
