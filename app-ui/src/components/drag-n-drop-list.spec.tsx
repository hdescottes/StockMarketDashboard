import { newModel } from "../model/model";
import { DragNDropList } from "./drag-n-drop-list";
import { render, screen } from "@testing-library/react";

describe("Drag n Drop list component", () => {
  it("should render", () => {
    const value1 = "toto1";
    const value2 = "toto2";
    const model1 = {
      ...newModel,
      value: value1,
    };
    const model2 = {
      ...newModel,
      value: value2,
    };
    render(<DragNDropList list={[model1, model2]} />);
    expect(screen.getByText(value1)).toBeInTheDocument();
    expect(screen.getByText(value2)).toBeInTheDocument();
  });
});
