import { Box, useTheme } from "@mui/material";
import { tokens } from "../theme";
import { Stock } from "../model/stock";
import "./stock-resume.scss";
import { useEffect, useState } from "react";
import { DetailColumn } from "./detail-column";

export const StockResume = (props: { stock: Stock }) => {
  const theme = useTheme();
  const colors = tokens(theme.palette.mode);
  const [variation, setVariation] = useState("");
  const [variationColor, setVariationColor] = useState("");

  useEffect(() => {
    intraDayVariation();
  });

  const intraDayVariation = () => {
    let value;
    let intraDayVar =
      ((props.stock.close - props.stock.open) / props.stock.open) * 100;

    if (intraDayVar > 0) {
      value = "+" + intraDayVar.toFixed(2) + "%";
      setVariationColor("positive");
    } else {
      value = intraDayVar.toFixed(2) + "%";
      setVariationColor("negative");
    }
    setVariation(value);
  };

  return (
    <Box
      gridColumn="span 3"
      bgcolor={colors.primary[900]}
      display="flex"
      alignItems="center"
      justifyContent="center"
    >
      <DetailColumn column={props.stock.symbol} colors={colors.grey[100]} />
      <DetailColumn column={props.stock.name} colors={colors.grey[100]} />
      <DetailColumn
        column={props.stock.close.toString()}
        colors={colors.grey[100]}
      />
      <DetailColumn
        column={props.stock.high.toString()}
        colors={colors.grey[100]}
      />
      <DetailColumn
        column={props.stock.low.toString()}
        colors={colors.grey[100]}
      />
      <DetailColumn
        column={props.stock.volume.toString()}
        colors={colors.grey[100]}
      />
      <DetailColumn column={variation} className={variationColor} />
    </Box>
  );
};
