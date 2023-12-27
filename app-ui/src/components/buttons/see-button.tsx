import Button from "@mui/material/Button";
import VisibilityIcon from "@mui/icons-material/Visibility";
import "./see-button.scss";

export const SeeButton = (props: { title: string; onClick: () => void }) => {
  return (
    <Button
      id={props.title}
      size="medium"
      aria-label={props.title}
      className="button"
      type="submit"
      onClick={props.onClick}
    >
      <VisibilityIcon sx={{ marginRight: "5px" }} />
      {props.title}
    </Button>
  );
};
