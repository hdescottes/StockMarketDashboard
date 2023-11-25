import { Section } from "./section";
import { render } from "@testing-library/react";

describe("Section component", () => {
  it("should render", () => {
    const dom = render(
      <Section>
        <div />
      </Section>
    );
    expect(dom).toBeTruthy();
  });
});
