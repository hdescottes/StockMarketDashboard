const { createProxyMiddleware } = require("http-proxy-middleware");

const apiHost = process.env.REACT_APP_API_HOST;

module.exports = function (app) {
  app.use(
    "/api",
    createProxyMiddleware({
      target: `http://${apiHost}:8080/api`,
      changeOrigin: true,
    })
  );
  app.use(
    "/v1",
    createProxyMiddleware({
      target: "http://api.marketstack.com/v1",
      changeOrigin: true,
    })
  );
};
