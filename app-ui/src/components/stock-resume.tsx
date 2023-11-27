import { Box, Typography, useTheme } from "@mui/material";
import { tokens } from "../theme";
import { Stock } from "../model/stock";

export const StockResume = (props: { stock: Stock }) => {
  const theme = useTheme();
  const colors = tokens(theme.palette.mode);

  return (
    <Box
      gridColumn="span 3"
      bgcolor={colors.primary[900]}
      display="flex"
      alignItems="center"
      justifyContent="center"
    >
      <Typography variant="h5" fontWeight="600" color={colors.grey[100]}>
        {props.stock.symbol}
      </Typography>
    </Box>
  );
};
