import { useEffect, useState } from "react";
import { Model } from "../model/model";
import { StockResume } from "./stock-resume";
import "./drag-n-drop-list.scss";
import { Reorder } from "framer-motion";

export const DragNDropList = (props: { list: Model[] }) => {
  const [list, setList] = useState<Model[]>([]);

  useEffect(() => {
    setList(props.list);
  }, [props.list]);

  return (
    <Reorder.Group className="ul" axis="y" values={list} onReorder={setList}>
      {list.map((item) => (
        <Reorder.Item className="item" key={item.id} value={item}>
          <StockResume model={item} />
        </Reorder.Item>
      ))}
    </Reorder.Group>
  );
};
