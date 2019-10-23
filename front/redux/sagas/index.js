import { all, fork } from 'redux-saga/effects';
import axios from 'axios';

import { SERVER_URL } from '@utils/consts';

axios.defaults.baseURL = SERVER_URL;

import user from './user';
import book from './book';
import category from './category';
import lending from './lending';
import register from './register';

export default function*() {
  yield all([fork(user), fork(book), fork(category), fork(lending), fork(register)]);
}
