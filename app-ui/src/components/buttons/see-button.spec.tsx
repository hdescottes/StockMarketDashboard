import { render, screen } from "@testing-library/react";
import { SeeButton } from "./see-button";

describe("See Button component", () => {
  it("should render", () => {
    const title = "titletest";
    render(<SeeButton title={title} onClick={jest.fn()} />);
    expect(screen.getByRole("button")).toHaveTextContent(title);
  });
});
