import ModelTest from "../components/model-test";
import { useDashboard } from "./use-dashboard.hook";
import { newModel } from "../../../model/model";

export const Dashboard = () => {
  const { model, setModel, create, models } = useDashboard(newModel);

  return (
    <ModelTest
      model={model}
      onChange={setModel}
      create={create}
      models={models}
    />
  );
};
