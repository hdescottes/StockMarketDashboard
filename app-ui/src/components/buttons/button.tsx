import { Button } from "@mui/material";
import "./button.scss";

export const ButtonCustom = (props: { title: string; onClick: () => void }) => {
  return (
    <Button
      id={props.title.replace(/\s/g, "")}
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
