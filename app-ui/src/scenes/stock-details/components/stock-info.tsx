import { useTheme } from "@mui/material";
import { Stock } from "../../../model/stock";
import { tokens } from "../../../theme";
import { Section } from "../../../components/section";
import Box from "@mui/material/Box";
import Typography from "@mui/material/Typography";
import { useEffect, useState } from "react";
import "./stock-info.scss";
import Grid from "@mui/material/Grid";

export const StockInfo = (props: { stock: Stock; stockDayBefore: Stock }) => {
  const theme = useTheme();
  const colors = tokens(theme.palette.mode);
  const [variation, setVariation] = useState("");
  const [variationColor, setVariationColor] = useState("");

  useEffect(() => {
    rateOfChange();
  });

  const rateOfChange = () => {
    let value;
    let rate =
      ((props.stock?.close - props.stockDayBefore?.close) /
        props.stockDayBefore?.close) *
      100;

    if (rate > 0) {
      value = "+" + rate.toFixed(2) + "%";
      setVariationColor("positive");
    } else {
      value = rate.toFixed(2) + "%";
      setVariationColor("negative");
    }
    setVariation(value);
  };

  return (
    <Section>
      <Grid container direction="row" spacing={5} className="p-2">
        <Grid item xs="auto">
          <Box display="flex" className="pb-4">
            <Typography
              data-testid="name"
              variant="h3"
              fontWeight="600"
              className="pe-2"
            >
              {props.stock?.name}
            </Typography>
            <Typography data-testid="symbol" variant="h3" fontWeight="600">
              {"(" + props.stock?.symbol + ")"}
            </Typography>
          </Box>
          <Box>
            <Box data-testid="close" display="flex">
              <Typography variant="h3" fontWeight="600" className="pe-1">
                {props.stock?.close}
              </Typography>
              <Typography className="pt-1">(c)</Typography>
            </Box>
            <Typography
              data-testid="variation"
              className={`${variationColor}`}
              variant="h5"
              fontWeight="600"
            >
              {variation}
            </Typography>
          </Box>
        </Grid>
        <Grid item xs={3}>
          <Box display="flex" className="ps-5 pb-3">
            <Box data-testid="open" className="pe-3">
              <Typography
                variant="h5"
                fontWeight="600"
                className="pb-1"
                color={colors.grey[600]}
              >
                Open
              </Typography>
              <Typography variant="h5" fontWeight="600">
                {props.stock?.open}
              </Typography>
            </Box>
            <Box data-testid="prevClose">
              <Typography
                variant="h5"
                fontWeight="600"
                className="pb-1"
                color={colors.grey[600]}
              >
                Previous close
              </Typography>
              <Typography variant="h5" fontWeight="600">
                {props.stockDayBefore?.close}
              </Typography>
            </Box>
          </Box>

          <Box display="flex" className="ps-5">
            <Box data-testid="high" className="pe-3">
              <Typography
                variant="h5"
                fontWeight="600"
                className="pb-1"
                color={colors.grey[600]}
              >
                High
              </Typography>
              <Typography variant="h5" fontWeight="600">
                {props.stock?.high}
              </Typography>
            </Box>
            <Box data-testid="low">
              <Typography
                variant="h5"
                fontWeight="600"
                className="pb-1"
                color={colors.grey[600]}
              >
                Low
              </Typography>
              <Typography variant="h5" fontWeight="600">
                {props.stock?.low}
              </Typography>
            </Box>
          </Box>
        </Grid>
        <Grid item xs={3}>
          <Box data-testid="volume" className="ps-5">
            <Typography
              variant="h5"
              fontWeight="600"
              className="pb-1"
              color={colors.grey[600]}
            >
              Volume
            </Typography>
            <Typography variant="h5" fontWeight="600">
              {props.stock?.volume}
            </Typography>
          </Box>
        </Grid>
        <Grid item xs={3}>
          <Box data-testid="date" className="ps-5">
            <Typography
              variant="h5"
              fontWeight="600"
              className="pb-1"
              color={colors.grey[600]}
            >
              Date
            </Typography>
            <Typography variant="h5" fontWeight="600">
              {props.stock?.date}
            </Typography>
          </Box>
        </Grid>
      </Grid>
    </Section>
  );
};
