import { useTheme } from "@mui/material";
import { tokens } from "../theme";

export const Section = (props: {
  children: React.ReactElement;
  className?: string;
}) => {
  const theme = useTheme();
  const colors = tokens(theme.palette.mode);
  return (
    <div
      className={"section p-3 " + props.className}
      style={{ backgroundColor: `${colors.primary[400]}` }}
    >
      {props.children}
    </div>
  );
};
