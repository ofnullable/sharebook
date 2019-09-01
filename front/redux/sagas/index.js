import { all, fork } from 'redux-saga/effects';
import axios from 'axios';

axios.defaults.baseURL = 'http://localhost:8081';

import user from './user';

export default function*() {
  yield all([fork(user)]);
}
