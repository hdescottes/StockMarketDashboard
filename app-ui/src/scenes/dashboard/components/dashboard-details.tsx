import Box from "@mui/material/Box";
import { DragNDropList } from "../../../components/drag-n-drop-list";
import { Model } from "../../../model/model";
import { Section } from "../../../components/section";

export const DashboardDetails = (props: { models: Model[] }) => {
  return (
    <Section className="mt-3">
      <Box
        sx={{
          ".MuiBox-root": {
            borderRadius: "8px",
          },
        }}
      >
        <DragNDropList list={props.models} />
      </Box>
    </Section>
  );
};
