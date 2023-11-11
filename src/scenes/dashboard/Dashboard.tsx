import { useTheme } from "@mui/material";
import { tokens } from "../../theme";
import Header from "../../components/Header";

const Dashboard = () => {
  const theme = useTheme();
  const colors = tokens(theme.palette.mode);

  return (
    <div>
      <Header title="DASHBOARD" subtitle="Welcome to your dashboard" />
    </div>
  );
};

export default Dashboard;
