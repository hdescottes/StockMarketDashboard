import { useEffect, useState } from "react";
import ReactApexChart from "react-apexcharts";
import { Stock } from "../../../model/stock";

export const StockChart = (props: { stocks: Stock[] }) => {
  const [candlestickSeries, setCandlestickSeries] = useState<
    { data: { x: string; y: number[] }[] }[]
  >([]);
  const [barSeries, setBarSeries] = useState<
    { name: "volume"; data: { x: string; y: number }[] }[]
  >([]);
  const [candleOptions, setCandleOptions] = useState<any>({
    chart: {
      type: "candlestick",
      height: 350,
      toolbar: {
        autoSelected: "pan",
        show: false,
      },
      zoom: {
        enabled: false,
      },
    },
    plotOptions: {
      candlestick: {
        colors: {
          upward: "#28c612",
          downward: "#c63212",
        },
      },
    },
    tooltip: {
      theme: "dark",
    },
  });
  const [barOptions, setBarOptions] = useState<any>({
    chart: {
      type: "bar",
      height: 290,
      toolbar: {
        autoSelected: "pan",
        show: false,
      },
      selection: {
        enabled: true,
        fill: {
          color: "#ccc",
          opacity: 0.4,
        },
        stroke: {
          color: "#0D47A1",
        },
      },
    },
    dataLabels: {
      enabled: false,
    },
    plotOptions: {
      bar: {
        columnWidth: "80%",
      },
    },
    tooltip: {
      theme: "dark",
    },
  });

  useEffect(() => {
    const reversedStocks = [...props.stocks].reverse();
    const transformedCandlestickData = reversedStocks.map((stock) => ({
      x: stock.date,
      y: [stock.open, stock.high, stock.low, stock.close],
    }));

    const transformedBarData = reversedStocks.map((stock) => ({
      x: stock.date,
      y: stock.volume,
    }));

    setCandlestickSeries([{ data: transformedCandlestickData }]);
    setBarSeries([{ name: "volume", data: transformedBarData }]);

    setCandleOptions((prevOptions: any) => ({
      ...prevOptions,
      xaxis: {
        type: "category",
        categories: reversedStocks.map((stock) => stock.date),
      },
    }));
    setBarOptions((prevOptions: any) => ({
      ...prevOptions,
      xaxis: {
        type: "category",
        categories: reversedStocks.map((stock) => stock.date),
      },
    }));
  }, [props.stocks]);

  return (
    <div className="chart-box">
      <div data-testid="chart-candlestick">
        <ReactApexChart
          options={candleOptions}
          series={candlestickSeries}
          type="candlestick"
          height={350}
        />
      </div>
      <div data-testid="chart-bar">
        <ReactApexChart
          options={barOptions}
          series={barSeries}
          type="bar"
          height={290}
        />
      </div>
    </div>
  );
};
