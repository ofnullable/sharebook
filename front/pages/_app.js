import React from 'react';
import axios from 'axios';
import Head from 'next/head';
import { Provider } from 'react-redux';
import withRedux from 'next-redux-wrapper';
import withReduxSaga from 'next-redux-saga';

import { loadUserRequest } from '@redux/actions/userActions';
import Layout from '@components/layout';
import store from '@redux/store';

import { GlobalStyle } from '@styles/global';

const title = 'public-share';
const description = 'share your books!';

const PublicShare = ({ Component, pageProps, store }) => {
  return (
    <Provider store={store}>
      <Head>
        <meta charSet='utf-8' />
        <title>public-share</title>
        <meta name='viewport' content='width=device-width, initial-scale=1.0' />
        <meta httpEquiv='X-UA-Compatible' content='IE=edge,chrome=1' />
        <meta name='title' content={title} />
        <meta name='description' content={description} />
        <link rel='icon' href='/static/favicon.ico' />
        <link href='https://fonts.googleapis.com/icon?family=Material+Icons' rel='stylesheet' />
        <link
          href='https://fonts.googleapis.com/css?family=Do+Hyeon|Fira+Mono:500&display=swap&subset=korean'
          rel='stylesheet'
        />
      </Head>
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
