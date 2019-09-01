import React from 'react';
import App from 'next/app';
import axios from 'axios';
import { Provider } from 'react-redux';
import withRedux from 'next-redux-wrapper';
import withReduxSaga from 'next-redux-saga';

import Layout from '../components/layout';
import store from '../redux/store';

import { GlobalStyle } from '../styles/global';

class PublicShare extends App {
  static async getInitialProps({ Component, ctx }) {
    const cookie = ctx.isServer ? ctx.req.headers.cookie : '';

    if (ctx.isServer && cookie) {
      axios.defaults.headers.cookie = cookie;
    }

    let pageProps = {};

    if (Component.getInitialProps) {
      pageProps = await Component.getInitialProps(ctx);
    }

    return { pageProps };
  }

  render() {
    const { Component, pageProps, store } = this.props;
    return (
      <Provider store={store}>
        <GlobalStyle />
        <Layout>
          <Component {...pageProps} />
        </Layout>
      </Provider>
    );
  }
}

export default withRedux(store)(withReduxSaga(PublicShare));
