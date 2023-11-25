import { render } from "@testing-library/react";
import { Dashboard } from "./dashboard.page";
import { ModelTest } from "../components/model-test";

jest.mock("../components/model-test");
const modelTest = ModelTest as jest.MockedFunction<typeof ModelTest>;

describe("Dashboard page", () => {
  it("Should return the Dashboard page", () => {
    const dom = render(<Dashboard />);

    expect(dom).toBeTruthy();
    expect(modelTest).toHaveBeenCalled();
  });
});
