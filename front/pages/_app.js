import React from 'react';
import axios from 'axios';
import { Provider } from 'react-redux';
import withRedux from 'next-redux-wrapper';
import withReduxSaga from 'next-redux-saga';

import { loadUserRequest } from '@redux/actions/userActions';
import Layout from '@components/layout';
import store from '@redux/store';

import { GlobalStyle } from '@styles/global';

const PublicShare = ({ Component, pageProps, store }) => {
  return (
    <Provider store={store}>
      <GlobalStyle />
      <Layout>
        <Component {...pageProps} />
      </Layout>
    </Provider>
  );
};

PublicShare.getInitialProps = async ({ Component, ctx }) => {
  const cookie = ctx.isServer ? ctx.req.headers.cookie : '';
  const loadUserNeeded = ctx.isServer && ['/signin', '/join'].includes(ctx.req.url);

  if (cookie) {
    axios.defaults.headers.cookie = cookie;
  }
  const state = ctx.store.getState();

  if (loadUserNeeded && !state.user.isSignedIn) {
    ctx.store.dispatch(loadUserRequest());
  }

  let pageProps = {};
  if (Component.getInitialProps) {
    pageProps = await Component.getInitialProps(ctx);
  }
  return { pageProps };
};

export default withRedux(store)(withReduxSaga(PublicShare));
