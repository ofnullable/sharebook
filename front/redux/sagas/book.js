import { fork, put, takeLatest, call, all } from 'redux-saga/effects';

import { BOOK } from '@redux/actionTypes';
import { loadBookListSuccess, loadBookListFailure } from '@redux/actions/bookActions';
import { loadBookListApi } from '@redux/api/book';

export default function*() {
  yield all([fork(watchLoadBookListRequest)]);
}

function* watchLoadBookListRequest() {
  yield takeLatest(BOOK.LOAD_BOOK_LIST_REQUEST, loadBookList);
}
function* loadBookList() {
  try {
    const response = yield call(loadBookListApi);
    yield put(loadBookListSuccess(response.data));
  } catch (e) {
    console.error(e);
    yield put(loadBookListFailure((e.response && e.response.data) || e));
  }
}
