import { fork, put, takeLatest, call, all, throttle, select } from 'redux-saga/effects';

import { BOOK } from '@redux/actionTypes';
import {
  loadBookListApi,
  loadBookListByCategoryApi,
  loadBookApi,
  loadMyBookListApi,
  loadMyBookListByRentalStatusApi,
} from '@redux/api/book';
import {
  loadBookListSuccess,
  loadBookListFailure,
  loadBookListByCategorySuccess,
  loadBookListByCategoryFailure,
  loadBookSuccess,
  loadBookFailure,
  loadMyBookListSuccess,
  loadMyBookListFailure,
  loadMyBookListByRentalStatusSuccess,
  loadMyBookListByRentalStatusFailure,
} from '@redux/actions/bookActions';

export default function*() {
  yield all([
    fork(watchLoadBookListRequest),
    fork(watchLoadBookListByCategoryRequest),
    fork(watchLoadBookRequest),
    fork(watchLoadMyBookListRequest),
    fork(watchLoadMyBookListByRentalStatusRequest),
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

function* watchLoadMyBookListRequest() {
  yield throttle(500, BOOK.LOAD_MY_BOOK_LIST_REQUEST, loadMyBookList);
}
function* loadMyBookList({ page, size }) {
  try {
    const response = yield call(loadMyBookListApi, { page, size });
    yield put(loadMyBookListSuccess(response.data));
  } catch (e) {
    console.error(e);
    yield put(loadMyBookListFailure(e.response.data || e));
  }
}

function* watchLoadMyBookListByRentalStatusRequest() {
  yield takeLatest(BOOK.LOAD_MY_BOOK_LIST_BY_RENTAL_STATUS_REQUEST, loadMyBookListByRentalStatus);
}
function* loadMyBookListByRentalStatus({ status, page, size }) {
  try {
    const response = yield call(loadMyBookListByRentalStatusApi, { status, page, size });
    yield put(loadMyBookListByRentalStatusSuccess(response.data));
  } catch (e) {
    console.error(e);
    yield put(loadMyBookListByRentalStatusFailure(e.response.data || e));
  }
}
