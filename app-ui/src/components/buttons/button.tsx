import { Button } from "@mui/material";
import "./button.scss";

export const ButtonCustom = (props: { id: string; title: string; onClick: () => void }) => {
  return (
    <Button
      id={props.id}
      size="medium"
      aria-label={props.title}
      className="button"
      type="submit"
      onClick={props.onClick}
    >
      {props.title}
    </Button>
  );
};
