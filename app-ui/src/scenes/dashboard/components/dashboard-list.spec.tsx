import { render } from "@testing-library/react";
import { DragNDropList } from "../../../components/drag-n-drop-list";
import { DashboardList } from "./dashboard-list";

jest.mock("../../../components/drag-n-drop-list");
const dragNDropList = DragNDropList as jest.MockedFunction<
  typeof DragNDropList
>;

describe("DashboardDetails component", () => {
  it("should render all fields", () => {
    const dom = render(<DashboardList stocks={[]} />);

    expect(dom).toBeTruthy();
    expect(dragNDropList).toHaveBeenCalled();
  });
});
