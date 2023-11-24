import { Button } from "@mui/material";
import "./button.scss";

export const ButtonCustom = (props: { title: string; onClick: () => void }) => {
  return (
    <Button
      size="medium"
      aria-label="create"
      className="button"
      type="submit"
      onClick={props.onClick}
    >
      {props.title}
    </Button>
  );
};
