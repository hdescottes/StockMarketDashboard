import { Box } from "@mui/material";
import { Model } from "../../../model/model";
import { Header } from "../../../components/header";
import { ButtonCustom } from "../../../components/button";
import { DragNDropList } from "../../../components/drag-n-drop-list";

interface ModelProps {
  model: Model;
  onChange: (value: Model) => void;
  create: () => void;
  models: Model[];
}

export function ModelTest({ model, onChange, create, models }: ModelProps) {
  const handleChange = (
    event: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>
  ) => {
    let value = undefined;

    if (event.target.value) {
      value = event.target.value;
    }

    onChange({
      ...model,
      [event.target.name]: value,
    });
  };

  return (
    <Box m="20px">
      {/* HEADER */}
      <Box display="flex" justifyContent="space-between" alignItems="center">
        <Header title="DASHBOARD" subtitle="Welcome to your dashboard" />
      </Box>
      <Box display="flex" justifyContent="flex" height="30px">
        <input aria-label="id" name="id" onChange={handleChange} />
        <input aria-label="value" name="value" onChange={handleChange} />
        <ButtonCustom title="create" onClick={create} />
      </Box>
      <Box
        paddingTop="50px"
        sx={{
          ".MuiBox-root": {
            borderRadius: "8px",
          },
        }}
      >
        <DragNDropList list={models} />
      </Box>
    </Box>
  );
}
