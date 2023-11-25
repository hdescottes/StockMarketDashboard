import { Box } from "@mui/material";
import { Model } from "../../../model/model";
import { ButtonCustom } from "../../../components/button";
import { Section } from "../../../components/section";

interface DashboardSearchProps {
  model: Model;
  onChange: (value: Model) => void;
  create: () => void;
}

export function DashboardSearch({
  model,
  onChange,
  create,
}: DashboardSearchProps) {
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
    <Section>
      <Box display="flex" justifyContent="flex" height="30px">
        <input aria-label="id" name="id" onChange={handleChange} />
        <input aria-label="value" name="value" onChange={handleChange} />
        <ButtonCustom title="create" onClick={create} />
      </Box>
    </Section>
  );
}
