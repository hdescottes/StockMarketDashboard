import { render } from "@testing-library/react";
import { StockDetails } from "./stock-details.page";
import { StockInfo } from "../components/stock-info";
import { StockChart } from "../components/stock-chart";

jest.mock("../components/stock-info");
const stockInfo = StockInfo as jest.MockedFunction<typeof StockInfo>;

jest.mock("../components/stock-chart");
const stockChart = StockChart as jest.MockedFunction<typeof StockChart>;

describe("Stock Details page", () => {
  it("Should return the Stock Details page", () => {
    const dom = render(<StockDetails />);

    expect(dom).toBeTruthy();
    expect(stockInfo).toHaveBeenCalled();
    expect(stockChart).toHaveBeenCalled();
  });
});
