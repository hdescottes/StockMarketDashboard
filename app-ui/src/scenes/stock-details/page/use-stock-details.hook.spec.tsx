import { renderHook, waitFor } from "@testing-library/react";
import { newStock } from "../../../model/stock";
import { StockDetailsService } from "../../../services/stock-details.service";
import { useStockDetails } from "./use-stock-details.hook";

jest.mock("../../../services/stock-details.service");
const stockDetailsService = StockDetailsService as jest.MockedClass<
  typeof StockDetailsService
>;

describe("useStockDetails", () => {
  beforeEach(() => {
    jest.clearAllMocks();
  });

  it("should call getBySymbol", async () => {
    stockDetailsService.prototype.getBySymbol.mockResolvedValue([newStock]);

    const { result } = renderHook(() => useStockDetails(""));

    await waitFor(() => {
      expect(stockDetailsService.prototype.getBySymbol).toHaveBeenCalled();
      expect(result.current.stocks).not.toBeNull();
    });
  });
});
