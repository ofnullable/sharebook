import { all, fork } from 'redux-saga/effects';

import user from './user';
import book from './book';
import category from './category';
import lending from './lending';
import review from './review';
import register from './register';

export default function*() {
  yield all([fork(user), fork(book), fork(category), fork(lending), fork(review), fork(register)]);
}
