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
    expect(screen.getByLabelText("search all")).toBeInTheDocument();
  });

  it("should call onChange when input is triggered", async () => {
    const newSymbol = "new value";
    const fetch = jest.fn();
    const onChangeMock = jest.fn();

    render(
      <DashboardSearch {...props} fetch={fetch} onChange={onChangeMock} />
    );

    const input = screen.getByLabelText("symbol");
    userEvent.type(input, newSymbol);
    userEvent.click(screen.getByLabelText("fetch"));

    expect(onChangeMock).toHaveBeenCalledWith({
      ...props.stock,
      symbol: newSymbol,
    });
    expect(fetch).toHaveBeenCalled();
  });

  it("should call search when button is clicked", async () => {
    const search = jest.fn();
    render(<DashboardSearch {...props} search={search} />);

    userEvent.click(screen.getByLabelText("search all"));
    expect(search).toHaveBeenCalled();
  });
});

const props = {
  stock: { ...newStock },
  onChange: jest.fn(),
  fetch: jest.fn(),
  search: jest.fn(),
  stocks: [newStock],
};
