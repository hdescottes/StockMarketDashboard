import Box from "@mui/material/Box";
import { DragNDropList } from "../../../components/drag-n-drop-list";
import { Section } from "../../../components/section";
import { Stock } from "../../../model/stock";

export const DashboardDetails = (props: { stocks: Stock[] }) => {
  return (
    <Section className="mt-3">
      <Box
        sx={{
          ".MuiBox-root": {
            borderRadius: "8px",
          },
        }}
      >
        <DragNDropList list={props.stocks} />
      </Box>
    </Section>
  );
};
