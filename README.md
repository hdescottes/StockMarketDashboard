# Stock market Dashboard
![build workflow](https://github.com/hdescottes/react-dashboard/actions/workflows/build.yml/badge.svg)

## Description
<p>This project is a stock market dashboard for my personal need.<br>
It uses the https://marketstack.com api to retrieve the stock information.<br>
You can fetch new stocks with the corresponding symbol (https://marketstack.com/search) and check its details up to 1 month (for now).
</p>

#### App-api
SpringBoot server with hibernate as ORM and Postgresql as database (with docker)

#### App-ui
React application
Cypress end-to-end tests

-------
## Demo
<p style="text-align:center">
  <img src="app-api/src/main/resources/demo/dashboard.png" width="509" height="226">
  <img src="app-api/src/main/resources/demo/stock-info.png" width="509" height="226">
</p>
<p style="text-align:center">demo with the new DA (can't show it on a public repo)</p>
