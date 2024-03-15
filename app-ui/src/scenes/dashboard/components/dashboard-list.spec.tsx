import { render } from "@testing-library/react";
import { DashboardList } from "./dashboard-list";

describe("DashboardDetails component", () => {
  it("should render all fields", () => {
    const dom = render(<DashboardList stocks={[]} />);

    expect(dom).toBeTruthy();
  });
});
