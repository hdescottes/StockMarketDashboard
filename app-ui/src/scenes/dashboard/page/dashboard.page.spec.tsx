import { render } from "@testing-library/react";
import { Dashboard } from "./dashboard.page";
import { DashboardSearch } from "../components/dashboard-search";
import { DashboardDetails } from "../components/dashboard-details";

jest.mock("../components/dashboard-search");
const dashBoardSearch = DashboardSearch as jest.MockedFunction<
  typeof DashboardSearch
>;
jest.mock("../components/dashboard-details");
const dashBoardDetails = DashboardDetails as jest.MockedFunction<
  typeof DashboardDetails
>;

describe("Dashboard page", () => {
  it("Should return the Dashboard page", () => {
    const dom = render(<Dashboard />);

    expect(dom).toBeTruthy();
    expect(dashBoardSearch).toHaveBeenCalled();
    expect(dashBoardDetails).toHaveBeenCalled();
  });
});
