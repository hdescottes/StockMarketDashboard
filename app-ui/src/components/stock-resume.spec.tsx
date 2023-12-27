import { newStock } from "../model/stock";
import { StockResume } from "./stock-resume";
import { render, screen } from "@testing-library/react";

jest.mock("react-router-dom", () => ({
  ...jest.requireActual("react-router-dom"),
  useNavigate: () => jest.fn(),
}));

describe("Stock Resume component", () => {
  it("should render with positive variation", () => {
    const value = "toto";
    const stock = {
      ...newStock,
      symbol: value,
      close: 10,
      open: 5,
    };
    const variation =
      "+" + (((stock.close - stock.open) / stock.open) * 100).toFixed(2) + "%";
    render(<StockResume stock={stock} />);
    expect(screen.getByText(value)).toBeInTheDocument();
    expect(screen.getByText(variation)).toBeInTheDocument();
  });

  it("should render with negative variation", () => {
    const value = "toto";
    const stock = {
      ...newStock,
      symbol: value,
      close: 5,
      open: 10,
    };
    const variation =
      (((stock.close - stock.open) / stock.open) * 100).toFixed(2) + "%";
    render(<StockResume stock={stock} />);
    expect(screen.getByText(value)).toBeInTheDocument();
    expect(screen.getByText(variation)).toBeInTheDocument();
  });
});
