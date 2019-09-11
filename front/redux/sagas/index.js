import { all, fork } from 'redux-saga/effects';
import axios from 'axios';

axios.defaults.baseURL = 'http://localhost:8081';

import user from './user';
import book from './book';
import category from './category';

export default function*() {
  yield all([fork(user), fork(book), fork(category)]);
}
