import { newStock } from "../model/stock";
import { DragNDropList } from "./drag-n-drop-list";
import { render, screen } from "@testing-library/react";

describe("Drag n Drop list component", () => {
  it("should render", () => {
    const value1 = "toto1";
    const value2 = "toto2";
    const stock1 = {
      ...newStock,
      symbol: value1,
    };
    const stock2 = {
      ...newStock,
      symbol: value2,
    };
    render(<DragNDropList list={[stock1, stock2]} />);
    expect(screen.getByText(value1)).toBeInTheDocument();
    expect(screen.getByText(value2)).toBeInTheDocument();
  });
});
