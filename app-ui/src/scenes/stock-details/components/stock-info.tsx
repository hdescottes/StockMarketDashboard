import { useTheme } from "@mui/material";
import { Stock } from "../../../model/stock";
import { tokens } from "../../../theme";
import { Section } from "../../../components/section";
import Box from "@mui/material/Box";
import Typography from "@mui/material/Typography";
import { useEffect, useState } from "react";
import "./stock-info.scss";
import Grid from "@mui/material/Grid";
import { SkeletonCustom } from "../../../components/skeleton";
import useIntl from "react-intl/src/components/useIntl";

export const StockInfo = (props: { stock: Stock; stockDayBefore: Stock }) => {
  const translate = useIntl();
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
        <Grid sx={{ flexGrow: "auto" }}>
          <Box display="flex" className="pb-4">
            <Typography
              data-testid="name"
              variant="h3"
              fontWeight="600"
              className="pe-2"
            >
              <SkeletonCustom
                data={props.stock}
                value={props.stock?.name}
                variant="text"
                width={200}
              />
            </Typography>
            <Typography data-testid="symbol" variant="h3" fontWeight="600">
              <SkeletonCustom
                data={props.stock}
                value={props.stock?.symbol}
                variant="text"
                width={100}
              />
            </Typography>
          </Box>
          <Box>
            <Box data-testid="close" display="flex">
              <Typography variant="h3" fontWeight="600" className="pe-1">
                <SkeletonCustom
                  data={props.stock}
                  value={props.stock?.close}
                  variant="text"
                  width={100}
                />
              </Typography>
              <Typography className="pt-1">(c)</Typography>
            </Box>
            <Typography
              data-testid="variation"
              className={`${variationColor}`}
              variant="h5"
              fontWeight="600"
            >
              <SkeletonCustom
                data={props.stock && props.stockDayBefore}
                value={variation}
                variant="text"
                width={100}
              />
            </Typography>
          </Box>
        </Grid>
        <Grid sx={{ flexGrow: 3 }}>
          <Box display="flex" className="ps-5 pb-3">
            <Box data-testid="open" className="pe-3">
              <Typography
                variant="h5"
                fontWeight="600"
                className="pb-1"
                color={colors.grey[600]}
              >
                {translate.formatMessage({ id: "stock.details.open" })}
              </Typography>
              <Typography variant="h5" fontWeight="600">
                <SkeletonCustom
                  data={props.stock}
                  value={props.stock?.open}
                  variant="text"
                  width={100}
                />
              </Typography>
            </Box>
            <Box data-testid="prevClose">
              <Typography
                variant="h5"
                fontWeight="600"
                className="pb-1"
                color={colors.grey[600]}
              >
                {translate.formatMessage({ id: "stock.details.previous.close" })}
              </Typography>
              <Typography variant="h5" fontWeight="600">
                <SkeletonCustom
                  data={props.stockDayBefore}
                  value={props.stockDayBefore?.close}
                  variant="text"
                  width={100}
                />
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
                {translate.formatMessage({ id: "stock.details.high" })}
              </Typography>
              <Typography variant="h5" fontWeight="600">
                <SkeletonCustom
                  data={props.stock}
                  value={props.stock?.high}
                  variant="text"
                  width={100}
                />
              </Typography>
            </Box>
            <Box data-testid="low">
              <Typography
                variant="h5"
                fontWeight="600"
                className="pb-1"
                color={colors.grey[600]}
              >
                {translate.formatMessage({ id: "stock.details.low" })}
              </Typography>
              <Typography variant="h5" fontWeight="600">
                <SkeletonCustom
                  data={props.stock}
                  value={props.stock?.low}
                  variant="text"
                  width={100}
                />
              </Typography>
            </Box>
          </Box>
        </Grid>
        <Grid sx={{ flexGrow: 3 }}>
          <Box data-testid="volume" className="ps-5">
            <Typography
              variant="h5"
              fontWeight="600"
              className="pb-1"
              color={colors.grey[600]}
            >
              {translate.formatMessage({ id: "stock.details.volume" })}
            </Typography>
            <Typography variant="h5" fontWeight="600">
              <SkeletonCustom
                data={props.stock}
                value={props.stock?.volume}
                variant="text"
                width={100}
              />
            </Typography>
          </Box>
        </Grid>
        <Grid sx={{ flexGrow: 3 }}>
          <Box data-testid="date" className="ps-5">
            <Typography
              variant="h5"
              fontWeight="600"
              className="pb-1"
              color={colors.grey[600]}
            >
              {translate.formatMessage({ id: "stock.details.date" })}
            </Typography>
            <Typography variant="h5" fontWeight="600">
              <SkeletonCustom
                data={props.stock}
                value={props.stock?.date}
                variant="text"
                width={100}
              />
            </Typography>
          </Box>
        </Grid>
      </Grid>
    </Section>
  );
};
