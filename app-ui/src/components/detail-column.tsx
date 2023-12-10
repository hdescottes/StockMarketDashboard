import { Container, Typography } from "@mui/material";

export const DetailColumn = (props: {
  column: string;
  colors?: any;
  className?: string;
}) => {
  return (
    <Container>
      <Typography
        className={`pt-1 pb-1 ${props.className}`}
        variant="h5"
        fontWeight="600"
        textAlign="center"
        color={props.colors}
      >
        {props.column}
      </Typography>
    </Container>
  );
};
