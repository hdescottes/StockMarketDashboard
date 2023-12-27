import { Box, useTheme } from "@mui/material";
import { tokens } from "../../../theme";
import { DragNDropList } from "../../../components/drag-n-drop-list";
import { Section } from "../../../components/section";
import { Stock } from "../../../model/stock";
import { DetailColumn } from "../../../components/detail-column";

const columns = [
  "Symbol",
  "Name",
  "Last",
  "High",
  "Low",
  "Volume",
  "Variation",
  "Action",
];

export const DashboardList = (props: { stocks: Stock[] }) => {
  const theme = useTheme();
  const colors = tokens(theme.palette.mode);

  return (
    <Section className="mt-3">
      <Box
        sx={{
          ".MuiBox-root": {
            borderRadius: "8px",
          },
        }}
      >
        <HeaderColumns colors={colors} />
        <DragNDropList list={props.stocks} />
      </Box>
    </Section>
  );
};

const HeaderColumns = (props: { colors: any }) => {
  return (
    <Box
      gridColumn="span 3"
      bgcolor={props.colors.primary[900]}
      display="flex"
      alignItems="center"
      justifyContent="center"
      className="mb-3"
    >
      {columns.map((column) => (
        <DetailColumn
          key={column}
          column={column}
          colors={props.colors.grey[100]}
        />
      ))}
    </Box>
  );
};
