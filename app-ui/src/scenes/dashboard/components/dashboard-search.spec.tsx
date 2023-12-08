import userEvent from "@testing-library/user-event";
import { DashboardSearch } from "./dashboard-search";
import { render, screen } from "@testing-library/react";
import { newStock } from "../../../model/stock";

describe("DashboardSearch component", () => {
  it("should render all fields", () => {
    const dom = render(<DashboardSearch {...props} />);

    expect(dom).toBeTruthy();
    expect(screen.getByLabelText("symbol")).toBeInTheDocument();
    expect(screen.getByLabelText("fetch")).toBeInTheDocument();
  });

  it("should call onChange when input are triggered", async () => {
    const fetch = jest.fn();
    render(<DashboardSearch {...props} fetch={fetch} />);

    userEvent.click(screen.getByLabelText("fetch"));
    expect(fetch).toHaveBeenCalled();
  });
});

const props = {
  stock: { ...newStock },
  onChange: jest.fn(),
  fetch: jest.fn(),
  stocks: [newStock],
};
