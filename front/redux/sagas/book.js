import { fork, put, takeLatest, call, all, throttle } from 'redux-saga/effects';

import { BOOK } from '@redux/actionTypes';
import {
  loadBookListApi,
  loadBookListByCategoryApi,
  loadBookApi,
  rentBookApi,
} from '@redux/api/book';
import {
  loadBookListSuccess,
  loadBookListFailure,
  loadBookListByCategorySuccess,
  loadBookListByCategoryFailure,
  loadBookSuccess,
  loadBookFailure,
  rentBookSuccess,
  rentBookFailure,
} from '@redux/actions/bookActions';

export default function*() {
  yield all([
    fork(watchLoadBookListRequest),
    fork(watchLoadBookListByCategoryRequest),
    fork(watchLoadBookRequest),
    fork(watchRentBookRequest),
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
    yield put(loadBookListFailure((e.response && e.response.data) || e));
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
    console.error((e.response && e.response.data) || e);
    yield put(loadBookListByCategoryFailure((e.response && e.response.data) || e));
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

function* watchRentBookRequest() {
  yield takeLatest(BOOK.RENT_BOOK_REQUEST, RentBook);
}
function* RentBook({ id }) {
  try {
    console.log(id);
    const response = yield call(rentBookApi, id);
    yield put(rentBookSuccess(response.data));
  } catch (e) {
    console.error(e);
    yield put(rentBookFailure((e.response && e.response.data) || e));
  }
}
