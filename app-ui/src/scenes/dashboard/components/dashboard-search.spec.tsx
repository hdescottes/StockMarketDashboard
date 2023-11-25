import userEvent from "@testing-library/user-event";
import { newModel } from "../../../model/model";
import { DashboardSearch } from "./dashboard-search";
import { render, screen } from "@testing-library/react";

describe("DashboardSearch component", () => {
  it("should render all fields", () => {
    const dom = render(<DashboardSearch {...props} />);

    expect(dom).toBeTruthy();
    expect(screen.getByLabelText("id")).toBeInTheDocument();
    expect(screen.getByLabelText("value")).toBeInTheDocument();
    expect(screen.getByLabelText("create")).toBeInTheDocument();
  });

  it("should call onChange when input are triggered", async () => {
    const create = jest.fn();
    render(<DashboardSearch {...props} create={create} />);

    userEvent.click(screen.getByLabelText("create"));
    expect(create).toHaveBeenCalled();
  });
});

const props = {
  model: { ...newModel },
  onChange: jest.fn(),
  create: jest.fn(),
  models: [newModel],
};
