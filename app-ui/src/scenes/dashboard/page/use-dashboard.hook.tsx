import { useState } from "react";
import { Model } from "../../../model/model";
import { DashboardService } from "../../../services/dashboard.service";

export const useDashboard = (newModel: Model) => {
  const [model, setModel] = useState(newModel);
  const [models, setModels] = useState<Model[]>([]);
  const dashboardService = new DashboardService();

  function create() {
    dashboardService.create(model).then(() => {
      search();
    });
  }

  function search() {
    dashboardService.search().then((models) => {
      if (models) {
        setModels(models);
      }
    });
  }

  return { model, setModel, create, models };
};
