const { createProxyMiddleware } = require("http-proxy-middleware");

module.exports = function (app) {
  app.use(
    "/api",
    createProxyMiddleware({
      target: "http://localhost:8080",
      changeOrigin: true,
    })
  );
  app.use(
    "/v1",
    createProxyMiddleware({
      target: "http://api.marketstack.com",
      changeOrigin: true,
    })
  );
};
