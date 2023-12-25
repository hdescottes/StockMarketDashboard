import { render, screen } from "@testing-library/react";
import { newStock } from "../../../model/stock";
import { StockChart } from "./stock-chart";

jest.mock("react-apexcharts");

describe("StockChart component", () => {
  it("should render all fields", () => {
    const stock = {
      ...newStock,
      symbol: "symbol",
      name: "name",
      open: 105,
      close: 150,
      high: 160,
      low: 100,
      volume: 100000,
      date: "date",
    };
    const stock2 = {
      ...newStock,
      symbol: "symbol",
      open: 90,
      close: 100,
      high: 120,
      low: 80,
      volume: 105000,
    };
    const dom = render(<StockChart stocks={[stock, stock2]} />);

    expect(dom).toBeTruthy();
    expect(screen.getByTestId("chart-candlestick")).toBeInTheDocument();
    expect(screen.getByTestId("chart-bar")).toBeInTheDocument();
  });
});
