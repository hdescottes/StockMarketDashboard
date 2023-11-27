import { newStock } from "../model/stock";
import { StockResume } from "./stock-resume";
import { render, screen } from "@testing-library/react";

describe("Stock Resume component", () => {
  it("should render", () => {
    const value = "toto";
    const stock = {
      ...newStock,
      symbol: value,
    };
    render(<StockResume stock={stock} />);
    expect(screen.getByText(value)).toBeInTheDocument();
  });
});
