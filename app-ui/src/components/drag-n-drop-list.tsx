import { useEffect, useState } from "react";
import { StockResume } from "./stock-resume";
import "./drag-n-drop-list.scss";
import { Reorder } from "framer-motion";
import { Stock } from "../model/stock";

export const DragNDropList = (props: { list: Stock[] }) => {
  const [list, setList] = useState<Stock[]>([]);

  useEffect(() => {
    setList(props.list);
  }, [props.list]);

  return (
    <Reorder.Group className="ul" axis="y" values={list} onReorder={setList}>
      {list.map((item) => (
        <Reorder.Item className="item" key={item.id} value={item}>
          <StockResume stock={item} />
        </Reorder.Item>
      ))}
    </Reorder.Group>
  );
};
