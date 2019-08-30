import { createStore, compose, applyMiddleware } from 'redux';
import createSagaMiddleware from 'redux-saga';

import reducer from '../reducers';
import saga from '../sagas';

export default (initialState, options) => {
  const sagaMiddleware = createSagaMiddleware();

  const reduxDevtools = !options.isServer && window.__REDUX_DEVTOOLS_EXTENSION__;

  const isProd = process.env.NODE_ENV === 'production';
  const enhancer = isProd
    ? compose(applyMiddleware(sagaMiddleware))
    : compose(
        applyMiddleware(sagaMiddleware),
        reduxDevtools ? window.__REDUX_DEVTOOLS_EXTENSION__() : f => f
      );

  const store = createStore(reducer, initialState, enhancer);
  store.sagaTask = sagaMiddleware.run(saga);
  return store;
};
