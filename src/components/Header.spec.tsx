import { render } from "@testing-library/react";
import Header from "./Header";

describe("Header component", () => {
  it("should render", () => {
    const title = "title";
    const subtitle = "subtitle";
    const dom = render(<Header title={title} subtitle={subtitle} />);
    expect(dom).toBeTruthy();
    expect(dom.getByText(title)).toBeInTheDocument();
    expect(dom.getByText(subtitle)).toBeInTheDocument();
  });
});
