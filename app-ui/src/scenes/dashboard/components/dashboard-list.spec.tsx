import { render } from "@testing-library/react";
import { DashboardList } from "./dashboard-list";
import { IntlWrapper } from "../../../test-utils/intlWrapper";

describe("DashboardDetails component", () => {
  it("should render all fields", () => {
    const dom = render(
      <DashboardList stocks={[]} />,
      { wrapper: IntlWrapper });

    expect(dom).toBeTruthy();
  });
});
