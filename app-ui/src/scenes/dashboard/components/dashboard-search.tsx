import { Box } from "@mui/material";
import { ButtonCustom } from "../../../components/buttons/button";
import { Section } from "../../../components/section";
import { Stock } from "../../../model/stock";
import { useIntl } from "react-intl";

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
  const translate = useIntl();
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
          <ButtonCustom id='fetch' title={translate.formatMessage({ id: "button.fetch" })} onClick={fetch} />
          <ButtonCustom id='searchall' title={translate.formatMessage({ id: "button.search.all" })} onClick={search} />
        </Box>
      </Section>
    </div>
  );
}
