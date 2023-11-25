import { render } from "@testing-library/react";
import { DragNDropList } from "../../../components/drag-n-drop-list";
import { DashboardDetails } from "./dashboard-details";

jest.mock("../../../components/drag-n-drop-list");
const dragNDropList = DragNDropList as jest.MockedFunction<
  typeof DragNDropList
>;

describe("DashboardDetails component", () => {
  it("should render all fields", () => {
    const dom = render(<DashboardDetails models={[]} />);

    expect(dom).toBeTruthy();
    expect(dragNDropList).toHaveBeenCalled();
  });
});
