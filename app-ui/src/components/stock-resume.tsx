import { Box, Typography, useTheme } from "@mui/material";
import { tokens } from "../theme";
import { Model } from "../model/model";

export const StockResume = (props: { model: Model }) => {
  const theme = useTheme();
  const colors = tokens(theme.palette.mode);

  return (
    <Box
      gridColumn="span 3"
      bgcolor={colors.primary[400]}
      display="flex"
      alignItems="center"
      justifyContent="center"
    >
      <Typography variant="h5" fontWeight="600" color={colors.grey[100]}>
        {props.model.value}
      </Typography>
    </Box>
  );
};
