export interface Stock {
  id: string;
  date: string;
  symbol: string;
  volume: number;
  open: number;
  close: number;
  high: number;
  low: number;
}

export const newStock: Stock = {
  id: "",
  date: "",
  symbol: "",
  volume: 0,
  open: 0,
  close: 0,
  high: 0,
  low: 0,
};
