import axios from "axios";
import { HttpService } from "./http.service";

jest.mock("axios");

const axiosMock = axios as jest.Mocked<typeof axios>;

const axiosDefaultHeaders = {
  "Content-Type": "application/json",
};

describe("HttpService", () => {
  it("should call axios get method", async () => {
    const httpService = new HttpService();

    axiosMock.get.mockImplementationOnce(() =>
      Promise.resolve({ data: "aaaaaaaaa" })
    );

    const result = await httpService.get("url", "params");

    expect(result).toBe("aaaaaaaaa");
    expect(axios.get).toHaveBeenCalledWith("url", {
      headers: axiosDefaultHeaders,
      params: "params",
    });
  });

  it("should call axios post method", async () => {
    const httpService = new HttpService();
    const body = "bodypost";

    axiosMock.post.mockImplementationOnce(() =>
      Promise.resolve({ data: "aaaaaaaaa" })
    );

    const result = await httpService.post("url", body);

    expect(result).toBe("aaaaaaaaa");
    expect(axios.post).toHaveBeenCalledWith("url", JSON.stringify(body), {
      headers: axiosDefaultHeaders,
    });
  });

  it("should call axios put method", async () => {
    const httpService = new HttpService();
    const body = "bodypost";

    axiosMock.put.mockImplementationOnce(() =>
      Promise.resolve({ data: "aaaaaaaaa" })
    );

    const result = await httpService.put("url", body);

    expect(result).toBe("aaaaaaaaa");
    expect(axios.put).toHaveBeenCalledWith("url", JSON.stringify(body), {
      headers: axiosDefaultHeaders,
    });
  });

  it("should call axios delete method", async () => {
    const httpService = new HttpService();

    axiosMock.delete.mockImplementationOnce(() =>
      Promise.resolve({ data: "aaaaaaaaa" })
    );

    const result = await httpService.delete("url");

    expect(result).toBe("aaaaaaaaa");
    expect(axios.delete).toHaveBeenCalledWith("url", {
      headers: axiosDefaultHeaders,
    });
  });
});
