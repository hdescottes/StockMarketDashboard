module.exports = (api) => {
  // Testing if babel is being run in test mode
  const isTest = api.env("test");
  api.cache(true);
  return {
    presets: [
      [
        "@babel/preset-react",
        {
          runtime: "automatic",
        },
      ],
      "@babel/preset-typescript",
      [
        "@babel/preset-env",
        {
          targets: {
            node: "current",
          },
          modules: isTest ? "commonjs" : false,
        },
      ],
    ],
  };
};
