import { Skeleton, SkeletonPropsVariantOverrides } from "@mui/material";
import { OverridableStringUnion } from "@mui/types";
import { Stock } from "../model/stock";

export const SkeletonCustom = (props: {
  data: Stock;
  value: string | number;
  variant: OverridableStringUnion<
    "text" | "rectangular" | "rounded" | "circular",
    SkeletonPropsVariantOverrides
  >;
  width: number;
}) => {
  return (
    <>
      {props.data ? (
        props.value
      ) : (
        <Skeleton variant={props.variant} width={props.width} />
      )}
    </>
  );
};
