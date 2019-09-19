import { fork, put, takeLatest, call, all } from 'redux-saga/effects';

import { BOOK_STATUS } from '@utils/consts';
import { RENTAL } from '@redux/actionTypes';
import { loadRentalInfoApi, loadRentalInfoByBookIdApi, rentalBookApi } from '@redux/api/rental';
import {
  loadRentalInfoSuccess,
  loadRentalInfoFailure,
  loadRentalInfoByBookIdSuccess,
  loadRentalInfoByBookIdFailure,
  rentalBookSuccess,
  rentalBookFailure,
} from '@redux/actions/rentalActions';
import { changeBookStatus } from '@redux/actions/bookActions';

export default function*() {
  yield all([
    fork(watchLoadRentalInfoRequest),
    fork(watchLoadRentalInfoByBookIdRequest),
    fork(watchRentalBookRequest),
  ]);
}

function* watchLoadRentalInfoRequest() {
  yield takeLatest(RENTAL.LOAD_MY_RENTAL_INFO_REQUEST, loadRentalInfo);
}
function* loadRentalInfo() {
  try {
    const response = yield call(loadRentalInfoApi);
    yield put(loadRentalInfoSuccess(response.data));
  } catch (e) {
    console.error(e);
    yield put(loadRentalInfoFailure(e.response.data || e));
  }
}

function* watchLoadRentalInfoByBookIdRequest() {
  yield takeLatest(RENTAL.LOAD_MY_RENTAL_INFO_BY_BOOK_ID_REQUEST, loadRentalInfoByBookId);
}
function* loadRentalInfoByBookId({ bookId }) {
  try {
    const response = yield call(loadRentalInfoByBookIdApi, bookId);
    yield put(loadRentalInfoByBookIdSuccess(response.data));
  } catch (e) {
    console.error(e);
    yield put(loadRentalInfoByBookIdFailure(e.response.data || e));
  }
}

function* watchRentalBookRequest() {
  yield takeLatest(RENTAL.RENTAL_BOOK_REQUEST, rentalBook);
}
function* rentalBook({ id }) {
  try {
    const response = yield call(rentalBookApi, id);
    yield put(rentalBookSuccess(response.data));
    yield put(changeBookStatus(response.data.bookId, BOOK_STATUS.UNAVAILABLE));
  } catch (e) {
    console.error(e);
    yield put(rentalBookFailure(e.response.data || e));
  }
}
