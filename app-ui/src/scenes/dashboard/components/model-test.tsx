import { Box, Typography, useTheme } from "@mui/material";
import { tokens } from "../../../theme";
import { Model } from "../../../model/model";
import { Header } from "../../../components/header";

interface ModelProps {
  model: Model;
  onChange: (value: Model) => void;
  create: () => void;
  models: Model[];
}

export function ModelTest({ model, onChange, create, models }: ModelProps) {
  const theme = useTheme();
  const colors = tokens(theme.palette.mode);

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
      <Box>
        <input aria-label="id" name="id" onChange={handleChange} />
        <input aria-label="value" name="value" onChange={handleChange} />
        <button aria-label="create" onClick={create} />
      </Box>
      <Box
        display="grid"
        gridTemplateColumns="repeat(12, 1fr)"
        gridAutoRows="140px"
        gap="20px"
      >
        {models.map((model) => (
          <Box
            gridColumn="span 3"
            bgcolor={colors.primary[400]}
            display="flex"
            alignItems="center"
            justifyContent="center"
          >
            <Typography variant="h5" fontWeight="600" color={colors.grey[100]}>
              {model.value}
            </Typography>
          </Box>
        ))}
      </Box>
    </Box>
  );
}

export default ModelTest;
