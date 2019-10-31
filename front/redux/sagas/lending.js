import { fork, put, takeLatest, call, all } from 'redux-saga/effects';

import { BOOK_STATUS, LENDING_STATUS } from '@utils/consts';
import { LENDING } from '@redux/actionTypes';
import { borrowBookApi, changeLendingStatusApi, loadLatestLendingApi, loadLendingListApi } from '@redux/api/lending';
import {
  borrowBookSuccess,
  borrowBookFailure,
  cancelBorrowSuccess,
  cancelBorrowFailure,
  acceptLendingSuccess,
  acceptLendingFailure,
  rejectLendingSuccess,
  rejectLendingFailure,
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
    fork(watchAcceptLendingRequest),
    fork(watchRejectLendingRequest),
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
  yield takeLatest(LENDING.CANCEL_BORROW_REQUEST, cancelBorrowBook);
}
function* cancelBorrowBook({ id }) {
  try {
    const response = yield call(changeLendingStatusApi, { id, status: LENDING_STATUS.CANCELED });
    yield put(cancelBorrowSuccess(response.data));
    yield put(changeBookStatus(response.data.book.id, BOOK_STATUS.AVAILABLE));
  } catch (e) {
    console.error(e);
    yield put(cancelBorrowFailure(e.response.data || e));
  }
}

function* watchAcceptLendingRequest() {
  yield takeLatest(LENDING.ACCEPT_LENDING_REQUEST, acceptLending);
}
function* acceptLending({ id }) {
  try {
    const response = yield call(changeLendingStatusApi, { id, status: LENDING_STATUS.ACCEPTED });
    yield put(acceptLendingSuccess(response.data));
  } catch (e) {
    console.error(e);
    yield put(acceptLendingFailure(e.response.data || e));
  }
}

function* watchRejectLendingRequest() {
  yield takeLatest(LENDING.REJECT_LENDING_REQUEST, rejectLending);
}
function* rejectLending({ id }) {
  try {
    const response = yield call(changeLendingStatusApi, { id, status: LENDING_STATUS.REJECTED });
    yield put(rejectLendingSuccess(response.data));
    yield put(changeBookStatus(response.data.book.id, BOOK_STATUS.AVAILABLE));
  } catch (e) {
    console.error(e);
    yield put(rejectLendingFailure(e.response.data || e));
  }
}

function* watchReturnBookRequest() {
  yield takeLatest(LENDING.RETURN_BOOK_REQUEST, returnBook);
}
function* returnBook({ id }) {
  try {
    const response = yield call(changeLendingStatusApi, { id, status: LENDING_STATUS.RETURNED });
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
