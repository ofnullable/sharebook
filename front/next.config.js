// const webpack = require('webpack');
const CompressionPlugin = require('compression-webpack-plugin');

const isProd = process.env.NODE_ENV === 'production';

module.exports = {
  webpack(config) {
    if (isProd) {
      config.plugins.push(new CompressionPlugin()); // main.js.gz
    }

    return {
      ...config,
      mode: isProd ? 'production' : 'development',
      devtool: isProd ? 'hidden-source-map' : 'eval',
      node: {
        fs: 'empty',
      },
    };
  },
};
