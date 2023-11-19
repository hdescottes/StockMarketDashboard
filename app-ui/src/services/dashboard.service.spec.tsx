import { Model } from "../model/model";
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
});
