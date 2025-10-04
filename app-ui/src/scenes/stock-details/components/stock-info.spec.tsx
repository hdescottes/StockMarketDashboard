import { render, screen } from "@testing-library/react";
import { newStock } from "../../../model/stock";
import { StockInfo } from "./stock-info";
import { IntlWrapper } from "../../../test-utils/intlWrapper";

describe("StockInfo component", () => {
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
      date: "prevDate",
      name: "prevName",
    };

    render(
      <StockInfo stock={stock} stockDayBefore={stock2} />,
      { wrapper: IntlWrapper }
    );

    expect(screen.getByTestId("symbol")).toBeInTheDocument();
    expect(screen.getByTestId("symbol")).toHaveTextContent(stock.symbol);

    expect(screen.getByTestId("name")).toBeInTheDocument();
    expect(screen.getByTestId("name")).toHaveTextContent(stock.name);

    expect(screen.getByTestId("variation")).toBeInTheDocument();
    const expectedVariation =
      ((stock.close - stock2.close) / stock2.close) * 100;
    const sign = expectedVariation >= 0 ? "+" : "";
    expect(screen.getByTestId("variation")).toHaveTextContent(
      `${sign}${expectedVariation.toFixed(2)}%`
    );

    expect(screen.getByTestId("open")).toBeInTheDocument();
    expect(screen.getByTestId("open")).toHaveTextContent(stock.open.toString());

    expect(screen.getByTestId("prevClose")).toBeInTheDocument();
    expect(screen.getByTestId("prevClose")).toHaveTextContent(
      stock2.close.toString()
    );

    expect(screen.getByTestId("close")).toBeInTheDocument();
    expect(screen.getByTestId("close")).toHaveTextContent(
      stock.close.toString()
    );

    expect(screen.getByTestId("high")).toBeInTheDocument();
    expect(screen.getByTestId("high")).toHaveTextContent(stock.high.toString());

    expect(screen.getByTestId("low")).toBeInTheDocument();
    expect(screen.getByTestId("low")).toHaveTextContent(stock.low.toString());

    expect(screen.getByTestId("volume")).toBeInTheDocument();
    expect(screen.getByTestId("volume")).toHaveTextContent(
      stock.volume.toString()
    );

    expect(screen.getByTestId("date")).toBeInTheDocument();
    expect(screen.getByTestId("date")).toHaveTextContent(stock.date);
  });
});
