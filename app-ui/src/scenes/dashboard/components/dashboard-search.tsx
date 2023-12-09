import { Box } from "@mui/material";
import { ButtonCustom } from "../../../components/button";
import { Section } from "../../../components/section";
import { Stock } from "../../../model/stock";

interface DashboardSearchProps {
  stock: Stock;
  onChange: (value: Stock) => void;
  fetch: () => void;
  search: () => void;
}

export function DashboardSearch({
  stock,
  onChange,
  fetch,
  search,
}: DashboardSearchProps) {
  const handleChange = (
    event: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>
  ) => {
    let value = undefined;

    if (event.target.value) {
      value = event.target.value;
    }

    onChange({
      ...stock,
      [event.target.name]: value,
    });
  };

  return (
    <div>
      <Section>
        <Box display="flex" justifyContent="flex" height="30px">
          <input aria-label="symbol" name="symbol" onChange={handleChange} />
          <ButtonCustom title="fetch" onClick={fetch} />
          <ButtonCustom title="search all" onClick={search} />
        </Box>
      </Section>
    </div>
  );
}
