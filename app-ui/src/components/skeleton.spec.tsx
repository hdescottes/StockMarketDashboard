import { render, screen } from "@testing-library/react";
import { SkeletonCustom } from "./skeleton";
import { createStock } from "../model/stock";

describe("Skeleton component", () => {
  it("should render without skeleton", () => {
    const stock = createStock();
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
