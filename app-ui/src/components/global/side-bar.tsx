import { useState } from "react";
import { useIntl } from "react-intl";
import { Sidebar, Menu, MenuItem } from "react-pro-sidebar";
import { Box, IconButton, Typography, useTheme } from "@mui/material";
import { useNavigate } from "react-router-dom";
import HomeOutlinedIcon from "@mui/icons-material/HomeOutlined";
import PeopleOutlinedIcon from "@mui/icons-material/PeopleOutlined";
import ContactsOutlinedIcon from "@mui/icons-material/ContactsOutlined";
import ReceiptOutlinedIcon from "@mui/icons-material/ReceiptOutlined";
import PersonOutlinedIcon from "@mui/icons-material/PersonOutlined";
import CalendarTodayOutlinedIcon from "@mui/icons-material/CalendarTodayOutlined";
import HelpOutlineOutlinedIcon from "@mui/icons-material/HelpOutlineOutlined";
import BarChartOutlinedIcon from "@mui/icons-material/BarChartOutlined";
import PieChartOutlineOutlinedIcon from "@mui/icons-material/PieChartOutlineOutlined";
import TimelineOutlinedIcon from "@mui/icons-material/TimelineOutlined";
import MenuOutlinedIcon from "@mui/icons-material/MenuOutlined";
import MapOutlinedIcon from "@mui/icons-material/MapOutlined";
import { tokens } from "../../theme";
import "./side-bar.scss";

const Item = ({
  title,
  to,
  icon,
  selected,
  setSelected,
}: {
  title: string;
  to: string;
  icon: any;
  selected: string;
  setSelected: any;
}) => {
  const theme = useTheme();
  const colors = tokens(theme.palette.mode);
  const navigate = useNavigate();
  const handleClick = () => {
    setSelected(title);
    navigate(`${to}`);
  };
  return (
    <MenuItem
      active={selected === title}
      color={colors.grey[100]}
      onClick={handleClick}
      icon={icon}
    >
      <Typography>{title}</Typography>
    </MenuItem>
  );
};

export const SideBar = () => {
  const translate = useIntl();
  const theme = useTheme();
  const colors = tokens(theme.palette.mode);
  const [isCollapsed, setIsCollapsed] = useState(false);
  const [selected, setSelected] = useState("Dashboard");

  return (
    <Box
      sx={{
        "& .ps-sidebar-container": {
          backgroundColor: `${colors.primary[400]} !important`,
        },
      }}
    >
      <Sidebar className="sidebar" collapsed={isCollapsed}>
        <Menu>
          <MenuItem
            className="menu-item"
            onClick={() => setIsCollapsed(!isCollapsed)}
            icon={isCollapsed ? <MenuOutlinedIcon /> : undefined}
            color={colors.grey[100]}
          >
            {!isCollapsed && (
              <Box
                display="flex"
                justifyContent="space-between"
                alignItems="center"
                ml="15px"
              >
                <Typography variant="h3" color={colors.grey[100]}>
                  ADMINIS
                </Typography>
                <IconButton onClick={() => setIsCollapsed(!isCollapsed)}>
                  <MenuOutlinedIcon />
                </IconButton>
              </Box>
            )}
          </MenuItem>

          {!isCollapsed && (
            <Box mb="25px">
              <Box textAlign="center">
                <Typography
                  className="typography-header-collapse"
                  variant="h2"
                  color={colors.grey[100]}
                  fontWeight="bold"
                >
                  Hugo Descottes
                </Typography>
                <Typography variant="h5" color={colors.greenAccent[500]}>
                  VP Admin
                </Typography>
              </Box>
            </Box>
          )}

          <Box>
            <Item
              title={translate.formatMessage({ id: "sidebar.dashboard" })}
              to="/"
              icon={<HomeOutlinedIcon />}
              selected={selected}
              setSelected={setSelected}
            />

            <Typography
              className="typography-item"
              variant="h6"
              color={colors.grey[300]}
            >
              {translate.formatMessage({ id: "sidebar.data" })}
            </Typography>
            <Item
              title={translate.formatMessage({ id: "sidebar.manageTeam" })}
              to="/team"
              icon={<PeopleOutlinedIcon />}
              selected={selected}
              setSelected={setSelected}
            />
            <Item
              title={translate.formatMessage({ id: "sidebar.contacts" })}
              to="/contacts"
              icon={<ContactsOutlinedIcon />}
              selected={selected}
              setSelected={setSelected}
            />
            <Item
              title={translate.formatMessage({ id: "sidebar.invoices" })}
              to="/invoices"
              icon={<ReceiptOutlinedIcon />}
              selected={selected}
              setSelected={setSelected}
            />

            <Typography
              className="typography-item"
              variant="h6"
              color={colors.grey[300]}
            >
              {translate.formatMessage({ id: "sidebar.pages" })}
            </Typography>
            <Item
              title={translate.formatMessage({ id: "sidebar.profile" })}
              to="/form"
              icon={<PersonOutlinedIcon />}
              selected={selected}
              setSelected={setSelected}
            />
            <Item
              title={translate.formatMessage({ id: "sidebar.calendar" })}
              to="/calendar"
              icon={<CalendarTodayOutlinedIcon />}
              selected={selected}
              setSelected={setSelected}
            />
            <Item
              title={translate.formatMessage({ id: "sidebar.faq" })}
              to="/faq"
              icon={<HelpOutlineOutlinedIcon />}
              selected={selected}
              setSelected={setSelected}
            />

            <Typography
              className="typography-item"
              variant="h6"
              color={colors.grey[300]}
            >
              {translate.formatMessage({ id: "sidebar.charts" })}
            </Typography>
            <Item
              title={translate.formatMessage({ id: "sidebar.bar" })}
              to="/bar"
              icon={<BarChartOutlinedIcon />}
              selected={selected}
              setSelected={setSelected}
            />
            <Item
              title={translate.formatMessage({ id: "sidebar.pie" })}
              to="/pie"
              icon={<PieChartOutlineOutlinedIcon />}
              selected={selected}
              setSelected={setSelected}
            />
            <Item
              title={translate.formatMessage({ id: "sidebar.line" })}
              to="/line"
              icon={<TimelineOutlinedIcon />}
              selected={selected}
              setSelected={setSelected}
            />
            <Item
              title={translate.formatMessage({ id: "sidebar.geography" })}
              to="/geography"
              icon={<MapOutlinedIcon />}
              selected={selected}
              setSelected={setSelected}
            />
          </Box>
        </Menu>
      </Sidebar>
    </Box>
  );
};
