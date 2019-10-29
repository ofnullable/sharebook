import { fork, put, takeLatest, call, all } from 'redux-saga/effects';

import { BOOK_STATUS } from '@utils/consts';
import { LENDING } from '@redux/actionTypes';
import { borrowBookApi, cancelBorrowBookApi, returnBookApi, loadLatestLendingApi, loadLendingListApi } from '@redux/api/lending';
import {
  borrowBookSuccess,
  borrowBookFailure,
  cancelBorrowBookSuccess,
  cancelBorrowBookFailure,
  returnBookSuccess,
  returnBookFailure,
  loadLatestLendingSuccess,
  loadLatestLendingFailure,
  loadLendingListSuccess,
  loadLendingListFailure,
} from '@redux/actions/lendingActions';
import { changeBookStatus } from '@redux/actions/bookActions';

export default function*() {
  yield all([
    fork(watchBorrowBookRequest),
    fork(watchCancelBorrowBookRequest),
    fork(watchReturnBookRequest),
    fork(watchLoadLatestLendingRequest),
    fork(watchLoadLendingListRequest)
  ]);
}

function* watchBorrowBookRequest() {
  yield takeLatest(LENDING.BORROW_BOOK_REQUEST, borrowBook);
}
function* borrowBook({ id }) {
  try {
    const response = yield call(borrowBookApi, id);
    yield put(borrowBookSuccess(response.data));
    yield put(changeBookStatus(response.data.book.id, BOOK_STATUS.UNAVAILABLE));
  } catch (e) {
    console.error(e);
    yield put(borrowBookFailure(e.response.data || e));
  }
}

function* watchCancelBorrowBookRequest() {
  yield takeLatest(LENDING.CANCEL_BORROW_BOOK_REQUEST, cancelBorrowBook);
}
function* cancelBorrowBook({ id }) {
  try {
    const response = yield call(cancelBorrowBookApi, id);
    yield put(cancelBorrowBookSuccess(response.data));
    yield put(changeBookStatus(response.data.book.id, BOOK_STATUS.AVAILABLE));
  } catch (e) {
    console.error(e);
    yield put(cancelBorrowBookFailure(e.response.data || e));
  }
}

function* watchReturnBookRequest() {
  yield takeLatest(LENDING.RETURN_BOOK_REQUEST, returnBook);
}
function* returnBook({ id }) {
  try {
    const response = yield call(returnBookApi, id);
    yield put(returnBookSuccess(response.data));
    yield put(changeBookStatus(response.data.book.id, BOOK_STATUS.AVAILABLE));
  } catch (e) {
    console.error(e);
    yield put(returnBookFailure(e.response.data || e));
  }
}

function* watchLoadLatestLendingRequest() {
  yield takeLatest(LENDING.LOAD_LATEST_LENDING_REQUEST, loadLatestLending);
}
function* loadLatestLending({ bookId }) {
  try {
    const response = yield call(loadLatestLendingApi, bookId);
    yield put(loadLatestLendingSuccess(response.data));
  } catch (e) {
    yield put(loadLatestLendingFailure(e.response.data || e));
  }
}

function* watchLoadLendingListRequest() {
  yield takeLatest(LENDING.LOAD_LENDING_LIST_REQUEST, loadLendingList);
}
function* loadLendingList({ status, page, size }) {
  try {
    const response = yield call(loadLendingListApi, { status, page, size });
    yield put(loadLendingListSuccess(response.data));
  } catch (e) {
    console.error(e);
    yield put(loadLendingListFailure(e.response.data || e));
  }
}
