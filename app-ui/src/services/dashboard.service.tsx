import { Model } from "../model/model";
import { HttpService } from "./http.service";

export class DashboardService {
  httpService: HttpService = new HttpService();

  search(): Promise<Model[]> {
    return this.httpService.get<Model[]>("/api/stock-market").then(
      (response: Model[]) => response,
      (_error) => []
    );
  }

  create(model: Model): Promise<number | null> {
    return this.httpService
      .post<number>("/api/stock-market", {
        ...model,
      })
      .then(
        (response: number) => response,
        (_error) => null
      );
  }
}
