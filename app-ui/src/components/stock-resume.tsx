import { Box, Container, useTheme } from "@mui/material";
import { tokens } from "../theme";
import { Stock } from "../model/stock";
import "./stock-resume.scss";
import { useEffect, useState } from "react";
import { DetailColumn } from "./detail-column";
import { useNavigate } from "react-router-dom";
import { SeeButton } from "./buttons/see-button";

export const StockResume = (props: { stock: Stock }) => {
  const theme = useTheme();
  const colors = tokens(theme.palette.mode);
  const [variation, setVariation] = useState("");
  const [variationColor, setVariationColor] = useState("");
  const navigate = useNavigate();

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

  const redirectClick = () => {
    navigate(`/stocks/${props.stock.symbol}`);
  };

  return (
    <Box
      id={props.stock.symbol}
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
      <Container
        sx={{ display: "flex", justifyContent: "center", alignItems: "center" }}
      >
        <SeeButton title="See" onClick={redirectClick} />
      </Container>
    </Box>
  );
};
