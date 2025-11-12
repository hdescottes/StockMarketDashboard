export interface Stock {
  id: string;
  date: string;
  symbol: string;
  name: string;
  volume: number;
  open: number;
  close: number;
  high: number;
  low: number;
}

export const createStock = (overrides: Partial<Stock> = {}): Stock => ({
  id: "",
  date: "",
  symbol: "",
  name: "",
  volume: 0,
  open: 0,
  close: 0,
  high: 0,
  low: 0,
  ...overrides,
});
