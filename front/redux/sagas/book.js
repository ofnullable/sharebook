import { fork, put, takeLatest, call, all, throttle } from 'redux-saga/effects';

import { BOOK } from '@redux/actionTypes';
import { loadBookListApi } from '@redux/api/book';
import { loadBookListSuccess, loadBookListFailure } from '@redux/actions/bookActions';
import { loadBookApi } from '../api/book';
import { loadBookSuccess, loadBookFailure } from '../actions/bookActions';

export default function*() {
  yield all([fork(watchLoadBookListRequest), fork(watchLoadBookRequest)]);
}

function* watchLoadBookListRequest() {
  yield takeLatest(BOOK.LOAD_BOOK_LIST_REQUEST, loadBookList);
}
function* loadBookList({ page, size, searchText }) {
  try {
    const response = yield call(loadBookListApi, { page, size, searchText });
    yield put(loadBookListSuccess(response.data.content, response.data.last));
  } catch (e) {
    console.error(e);
    yield put(loadBookListFailure((e.response && e.response.data) || e));
  }
}

function* watchLoadBookRequest() {
  yield takeLatest(BOOK.LOAD_BOOK_REQUEST, loadBook);
}
function* loadBook({ id }) {
  try {
    const response = yield call(loadBookApi, id);
    yield put(loadBookSuccess(response.data));
  } catch (e) {
    console.error(e);
    yield put(loadBookFailure((e.response && e.response.data) || e));
  }
}
