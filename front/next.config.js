// const webpack = require('webpack');
const withBundleAnalyzer = require('@next/bundle-analyzer')({
  enabled: process.env.ANALYZE === 'true',
});
const CompressionPlugin = require('compression-webpack-plugin');

const isProd = process.env.NODE_ENV === 'production';

module.exports = withBundleAnalyzer({
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
});
