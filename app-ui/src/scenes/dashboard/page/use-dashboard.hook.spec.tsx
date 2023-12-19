import { act, renderHook, waitFor } from "@testing-library/react";
import { useDashboard } from "./use-dashboard.hook";
import { DashboardService } from "../../../services/dashboard.service";
import { newStock } from "../../../model/stock";

jest.mock("../../../services/dashboard.service");
const dashboardService = DashboardService as jest.MockedClass<
  typeof DashboardService
>;

describe("useDashboard", () => {
  beforeEach(() => {
    jest.clearAllMocks();
  });

  it("should call getById, fetch, then create and search", async () => {
    dashboardService.prototype.getLastWorkingDayBySymbol.mockResolvedValue(
      newStock
    );
    dashboardService.prototype.fetch.mockResolvedValue(newStock);
    dashboardService.prototype.create.mockResolvedValue(1);
    dashboardService.prototype.search.mockResolvedValue([]);

    const { result } = renderHook(() => useDashboard(newStock));

    act(() => {
      result.current.fetch();
    });

    await waitFor(() => {
      expect(
        dashboardService.prototype.getLastWorkingDayBySymbol
      ).toHaveBeenCalled();
      expect(dashboardService.prototype.fetch).toHaveBeenCalled();
      expect(dashboardService.prototype.create).toHaveBeenCalled();
      expect(dashboardService.prototype.search).toHaveBeenCalled();
      expect(result.current.stock).not.toBeNull();
    });
  });

  it("should call getById, then search", async () => {
    const dbStock = {
      ...newStock,
      id: "toto",
    };
    dashboardService.prototype.getLastWorkingDayBySymbol.mockResolvedValue(
      dbStock
    );
    dashboardService.prototype.search.mockResolvedValue([]);

    const { result } = renderHook(() => useDashboard(newStock));

    act(() => {
      result.current.fetch();
    });

    await waitFor(() => {
      expect(
        dashboardService.prototype.getLastWorkingDayBySymbol
      ).toHaveBeenCalled();
      expect(dashboardService.prototype.fetch).not.toHaveBeenCalled();
      expect(dashboardService.prototype.create).not.toHaveBeenCalled();
      expect(dashboardService.prototype.search).toHaveBeenCalled();
      expect(result.current.stock).not.toBeNull();
    });
  });
});
