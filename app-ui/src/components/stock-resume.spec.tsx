import { newModel } from "../model/model";
import { StockResume } from "./stock-resume";
import { render, screen } from "@testing-library/react";

describe("Stock Resume component", () => {
  it("should render", () => {
    const value = "toto";
    const model = {
      ...newModel,
      value: value,
    };
    render(<StockResume model={model} />);
    expect(screen.getByText(value)).toBeInTheDocument();
  });
});
