import { Section } from "./section";
import { render, screen } from "@testing-library/react";
import { SkeletonCustom } from "./skeleton";
import { Stock, newStock } from "../model/stock";

describe("Skeleton component", () => {
  it("should render without skeleton", () => {
    const stock = newStock;
    const dom = render(
      <SkeletonCustom
        data={stock}
        value={stock.high}
        variant={"text"}
        width={100}
      />
    );
    expect(dom).toBeTruthy();
    expect(screen.getByText(stock.high)).toBeInTheDocument();
  });
});
