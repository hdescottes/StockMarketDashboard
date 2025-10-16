import userEvent from "@testing-library/user-event";
import { DashboardSearch } from "./dashboard-search";
import { render, screen } from "@testing-library/react";
import { newStock } from "../../../model/stock";
import { IntlWrapper } from "../../../test-utils/intlWrapper";

describe("DashboardSearch component", () => {
  it("should render all fields", () => {
    render(
      <DashboardSearch {...props} />,
      { wrapper: IntlWrapper }
    );

    expect(screen.getByLabelText("symbol")).toBeInTheDocument();
    expect(screen.getByLabelText("Fetch")).toBeInTheDocument();
    expect(screen.getByLabelText("Search All")).toBeInTheDocument();
  });

  it("should call onChange when input is triggered", async () => {
    const newSymbol = "new value";
    const fetch = jest.fn();
    const onChangeMock = jest.fn();

    render(
      <DashboardSearch {...props} fetch={fetch} onChange={onChangeMock} />,
      { wrapper: IntlWrapper }
    );

    const input = screen.getByLabelText("symbol");
    await userEvent.type(input, newSymbol);
    await userEvent.click(screen.getByLabelText("Fetch"));

    expect(onChangeMock).toHaveBeenCalledWith({
      ...props.stock,
      symbol: newSymbol,
    });
    expect(fetch).toHaveBeenCalled();
  });

  it("should call search when button is clicked", async () => {
    const search = jest.fn();
    render(
      <DashboardSearch {...props} search={search} />,
      { wrapper: IntlWrapper }
    );

    await userEvent.click(screen.getByLabelText("Search All"));
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
