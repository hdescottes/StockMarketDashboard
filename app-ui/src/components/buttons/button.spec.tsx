import { render, screen } from "@testing-library/react";
import { ButtonCustom } from "./button";

describe("Button component", () => {
  it("should render", () => {
    const title = "titletest";
    render(<ButtonCustom id='button' title={title} onClick={jest.fn()} />);
    expect(screen.getByRole("button")).toHaveTextContent(title);
  });
});
