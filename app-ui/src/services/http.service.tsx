import axios from "axios";

export class HttpService {
  headers = {
    "Content-Type": "application/json",
  };

  delete<T>(url: string): Promise<T> {
    return axios
      .delete(`${url}`, {
        headers: this.headers,
      })
      .then((axiosResponse) => {
        return axiosResponse.data as T;
      });
  }

  post<T>(url: string, body: any): Promise<T> {
    return axios
      .post(`${url}`, JSON.stringify(body), {
        headers: this.headers,
      })
      .then((axiosResponse) => {
        return axiosResponse.data as T;
      });
  }

  put<T>(url: string, body: any): Promise<T> {
    return axios
      .put(`${url}`, JSON.stringify(body), {
        headers: this.headers,
      })
      .then((axiosResponse) => {
        return axiosResponse.data as T;
      });
  }

  get<T>(url: string, params?: any): Promise<T> {
    return axios
      .get(`${url}`, {
        headers: this.headers,
        params,
      })
      .then((axiosResponse) => {
        return axiosResponse.data as T;
      });
  }
}
