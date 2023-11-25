import Box from "@mui/material/Box";
import { DragNDropList } from "../../../components/drag-n-drop-list";
import { Model } from "../../../model/model";

export const DashboardDetails = (props: { models: Model[] }) => {
  return (
    <Box
      paddingTop="50px"
      sx={{
        ".MuiBox-root": {
          borderRadius: "8px",
        },
      }}
    >
      <DragNDropList list={props.models} />
    </Box>
  );
};
