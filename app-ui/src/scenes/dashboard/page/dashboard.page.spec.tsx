import { render } from "@testing-library/react";
import { Dashboard } from "./dashboard.page";
import { DashboardSearch } from "../components/dashboard-search";
import { DashboardList } from "../components/dashboard-list";
import { IntlWrapper } from "../../../test-utils/intlWrapper";

jest.mock("../components/dashboard-search");
const dashBoardSearch = DashboardSearch as jest.MockedFunction<
  typeof DashboardSearch
>;
jest.mock("../components/dashboard-list");
const dashBoardList = DashboardList as jest.MockedFunction<
  typeof DashboardList
>;

describe("Dashboard page", () => {

  it("Should return the Dashboard page", () => {
    const dom = render(
      <Dashboard />,
      { wrapper: IntlWrapper }
    );

    expect(dom).toBeTruthy();
    expect(dashBoardSearch).toHaveBeenCalled();
    expect(dashBoardList).toHaveBeenCalled();
  });
});
