import { newStock } from "../model/stock";
import { DetailColumn } from "./detail-column";
import { StockResume } from "./stock-resume";
import { render, screen } from "@testing-library/react";

describe("Detail Column component", () => {
  it("should render", () => {
    const value = "toto";
    render(<DetailColumn column={value} />);
    expect(screen.getByText(value)).toBeInTheDocument();
  });
});
