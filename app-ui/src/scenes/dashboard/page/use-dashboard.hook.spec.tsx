import { act, renderHook, waitFor } from "@testing-library/react";
import { useDashboard } from "./use-dashboard.hook";
import { newModel } from "../../../model/model";
import { DashboardService } from "../../../services/dashboard.service";

jest.mock("../../../services/dashboard.service");
const dashboardService = DashboardService as jest.MockedClass<
  typeof DashboardService
>;

describe("useDashboard", () => {
  beforeEach(() => {
    jest.clearAllMocks();
    dashboardService.prototype.create.mockResolvedValue(1);
    dashboardService.prototype.search.mockResolvedValue([]);
  });

  it("should call create and then search", async () => {
    const { result } = renderHook(() => useDashboard(newModel));

    act(() => {
      result.current.create();
    });

    await waitFor(() => {
      expect(dashboardService.prototype.create).toHaveBeenCalled();
      expect(dashboardService.prototype.search).toHaveBeenCalled();
      expect(result.current.model).not.toBeNull();
    });
  });
});
