import userEvent from "@testing-library/user-event";
import { newModel } from "../../../model/model";
import ModelTest from "./model-test";
import { render, screen } from "@testing-library/react";

describe("ModelTest component", () => {
  it("should render all fields", () => {
    const dom = render(<ModelTest {...props} />);

    expect(dom).toBeTruthy();
    expect(screen.getByLabelText("id")).toBeInTheDocument();
    expect(screen.getByLabelText("value")).toBeInTheDocument();
    expect(screen.getByLabelText("create")).toBeInTheDocument();
  });

  it("should call onChange when input are triggered", async () => {
    const create = jest.fn();
    render(<ModelTest {...props} create={create} />);

    await userEvent.click(screen.getByLabelText("create"));
    expect(create).toHaveBeenCalled();
  });
});

const props = {
  model: { ...newModel },
  onChange: jest.fn(),
  create: jest.fn(),
  models: [newModel],
};
