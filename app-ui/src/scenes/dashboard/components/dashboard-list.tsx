import { Box, useTheme } from "@mui/material";
import { tokens } from "../../../theme";
import { Section } from "../../../components/section";
import { Stock } from "../../../model/stock";
import { useEffect, useMemo, useState } from "react";
import { AgGridReact, CustomCellRendererProps } from "@ag-grid-community/react";
import { ColDef, ModuleRegistry } from "@ag-grid-community/core";
import { ClientSideRowModelModule } from "@ag-grid-community/client-side-row-model";
import "@ag-grid-community/styles/ag-grid.css";
import "@ag-grid-community/styles/ag-theme-quartz.css";
import "./dashboard-list.scss";
import { SeeButton } from "../../../components/buttons/see-button";
import { useNavigate } from "react-router-dom";
import { useIntl } from "react-intl";

ModuleRegistry.registerModules([ClientSideRowModelModule]);

export const DashboardList = (props: { stocks: Stock[] }) => {
  return (
    <Section className="mt-3">
      <Box
        sx={{
          ".MuiBox-root": {
            borderRadius: "8px",
          },
        }}
      >
        <StockGrid stocks={props.stocks} />
      </Box>
    </Section>
  );
};

const VariationCellRenderer = (props: CustomCellRendererProps) => {
  const [variation, setVariation] = useState("");
  const [variationColor, setVariationColor] = useState("");

  useEffect(() => {
    intraDayVariation();
  });

  const intraDayVariation = () => {
    let value;
    let intraDayVar =
      ((props.data.close - props.data.open) / props.data.open) * 100;

    if (intraDayVar > 0) {
      value = "+" + intraDayVar.toFixed(2) + "%";
      setVariationColor("positive");
    } else {
      value = intraDayVar.toFixed(2) + "%";
      setVariationColor("negative");
    }
    setVariation(value);
  };

  return <div className={variationColor}>{variation}</div>;
};

const ActionCellRenderer = (props: CustomCellRendererProps) => {
  const navigate = useNavigate();
  const redirectClick = () => {
    navigate(`/stocks/${props.data.symbol}`);
  };

  return <SeeButton title="See" onClick={redirectClick} />;
};

const StockGrid = (props: { stocks: Stock[] }) => {
  const translate = useIntl();
  const theme = useTheme();
  const colors = tokens(theme.palette.mode);
  const containerStyle = useMemo(() => ({ width: "100%", height: "100%" }), []);
  const [columnDefs] = useState<ColDef[]>([
    { field: "symbol", headerName: translate.formatMessage({ id: "stock.grid.symbol" }), headerClass: "header" },
    { field: "name", headerName: translate.formatMessage({ id: "stock.grid.name" }), headerClass: "header" },
    { field: "close", headerName: translate.formatMessage({ id: "stock.grid.close" }), headerClass: "header" },
    { field: "high", headerName: translate.formatMessage({ id: "stock.grid.high" }), headerClass: "header" },
    { field: "low", headerName: translate.formatMessage({ id: "stock.grid.low" }), headerClass: "header" },
    { field: "volume", headerName: translate.formatMessage({ id: "stock.grid.volume" }), headerClass: "header" },
    {
      field: "variation", 
      headerName: translate.formatMessage({ id: "stock.grid.variation" }),
      headerClass: "header",
      cellRenderer: VariationCellRenderer,
    },
    {
      field: "action",
      headerName: translate.formatMessage({ id: "stock.grid.action" }),
      headerClass: "header",
      cellRenderer: ActionCellRenderer,
    },
  ]);
  const defaultColDef = useMemo<ColDef>(() => {
    return {
      width: 170,
      filter: true,
    };
  }, []);
  const rowStyle = {
    fontWeight: "600",
    background: colors.primary[400],
    color: colors.grey[100],
  };

  return (
    <div style={containerStyle}>
      <div className={"ag-theme-quartz"}>
        <AgGridReact<Stock>
          rowData={props.stocks}
          columnDefs={columnDefs}
          defaultColDef={defaultColDef}
          rowDragManaged={true}
          rowDragEntireRow={true}
          autoSizeStrategy={{
            type: "fitGridWidth",
          }}
          domLayout={"autoHeight"}
          rowStyle={rowStyle}
        />
      </div>
    </div>
  );
};
