// const webpack = require('webpack');
const withBundleAnalyzer = require('@next/bundle-analyzer')({
  enabled: process.env.ANALYZE === 'true',
});
const CompressionPlugin = require('compression-webpack-plugin');

const isProd = process.env.NODE_ENV === 'production';

module.exports = withBundleAnalyzer({
  webpack(config) {
    const plugins = [
      ...config.plugins,
      // new webpack.ContextReplacementPlugin(/moment[/\\]locale$/, /^\.\/ko$/),
    ];
    if (isProd) {
      plugins.push(new CompressionPlugin()); // main.js.gz
    }

    config = {
      ...config,
      plugins,
      mode: isProd ? 'production' : 'development',
      devtool: isProd ? 'hidden-source-map' : 'eval',
      node: {
        fs: 'empty',
      },
      resolve: {
        ...config.resolve,
        alias: {
          ...config.resolve.alias,
          '@components': './components',
          '@styles': './styles',
          '@redux': './redux',
          '@utils': './utils',
        },
      },
    };

    return config;
  },
});
