import { fork, put, takeLatest, call, all, throttle } from 'redux-saga/effects';

import { BOOK } from '@redux/actionTypes';
import { loadBookListApi, loadBookListByCategoryApi, loadBookApi } from '@redux/api/book';
import {
  loadBookListSuccess,
  loadBookListFailure,
  loadBookListByCategorySuccess,
  loadBookListByCategoryFailure,
  loadBookSuccess,
  loadBookFailure,
} from '@redux/actions/bookActions';

export default function*() {
  yield all([
    fork(watchLoadBookListRequest),
    fork(watchLoadBookListByCategoryRequest),
    fork(watchLoadBookRequest),
  ]);
}

function* watchLoadBookListRequest() {
  yield throttle(500, BOOK.LOAD_BOOK_LIST_REQUEST, loadBookList);
}
function* loadBookList({ searchText, page, size }) {
  try {
    const response = yield call(loadBookListApi, { searchText, page, size });
    yield put(loadBookListSuccess(response.data));
  } catch (e) {
    console.error(e);
    yield put(loadBookListFailure(e.response.data || e));
  }
}

function* watchLoadBookListByCategoryRequest() {
  yield takeLatest(BOOK.LOAD_BOOK_LIST_BY_CATEGORY_REQUEST, loadBookListByCategory);
}
function* loadBookListByCategory({ page, size, category }) {
  try {
    const response = yield call(loadBookListByCategoryApi, { page, size, category });
    yield put(loadBookListByCategorySuccess(response.data));
  } catch (e) {
    console.error(e);
    yield put(loadBookListByCategoryFailure(e.response.data || e));
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
    yield put(loadBookFailure(e.response.data || e));
  }
}
