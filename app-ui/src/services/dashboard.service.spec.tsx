import { Model, newModel } from "../model/model";
import { DashboardService } from "./dashboard.service";
import { HttpService } from "./http.service";

jest.mock("./http.service");
const httpService = HttpService as jest.MockedClass<typeof HttpService>;
const model = {} as Model;

describe("Dashboard service", () => {
  describe("Call to search models", () => {
    it("Succeeds, should return list of model", async () => {
      const dashboardService = new DashboardService();

      httpService.prototype.get.mockResolvedValue({ response: [model, model] });

      const response = await dashboardService.search();
      expect(httpService.prototype.get).toHaveBeenCalledWith(
        "/api/stock-market"
      );
      expect(response).toEqual({ response: [model, model] });
    });

    it("Fails, should return empty array", async () => {
      const dashboardService = new DashboardService();

      httpService.prototype.get.mockResolvedValue({ response: [] });

      const response = await dashboardService.search();
      expect(httpService.prototype.get).toHaveBeenCalledWith(
        "/api/stock-market"
      );
      expect(response).toEqual({ response: [] });
    });
  });

  describe("Call to create model", () => {
    it("Succeeds, should create a model", async () => {
      const dashboardService = new DashboardService();

      httpService.prototype.post.mockResolvedValue({ response: 1 });

      const response = await dashboardService.create(newModel);
      expect(httpService.prototype.post).toHaveBeenCalledWith(
        "/api/stock-market",
        newModel
      );
      expect(response).toEqual({ response: 1 });
    });

    it("Fails, should return null", async () => {
      const dashboardService = new DashboardService();

      httpService.prototype.post.mockResolvedValue({ response: null });

      const response = await dashboardService.create(newModel);
      expect(httpService.prototype.post).toHaveBeenCalledWith(
        "/api/stock-market",
        newModel
      );
      expect(response).toEqual({ response: null });
    });
  });
});
