import { fork, put, takeLatest, call, all } from 'redux-saga/effects';

import { BOOK_STATUS } from '@utils/consts';
import { RENTAL } from '@redux/actionTypes';
import { rentalBookApi, loadRentalListApi } from '@redux/api/rental';
import {
  rentalBookSuccess,
  rentalBookFailure,
  loadRentalListSuccess,
  loadRentalListFailure,
} from '@redux/actions/rentalActions';
import { changeBookStatus } from '@redux/actions/bookActions';

export default function*() {
  yield all([fork(watchRentalBookRequest), fork(watchLoadRentalListRequest)]);
}

function* watchRentalBookRequest() {
  yield takeLatest(RENTAL.RENTAL_BOOK_REQUEST, rentalBook);
}
function* rentalBook({ id }) {
  try {
    const response = yield call(rentalBookApi, id);
    yield put(rentalBookSuccess(response.data));
    yield put(changeBookStatus(response.data.book.id, BOOK_STATUS.UNAVAILABLE));
  } catch (e) {
    console.error(e);
    yield put(rentalBookFailure(e.response.data || e));
  }
}

function* watchLoadRentalListRequest() {
  yield takeLatest(RENTAL.LOAD_RENTAL_LIST_REQUEST, loadRentalList);
}
function* loadRentalList({ status, page, size }) {
  try {
    const response = yield call(loadRentalListApi, { status, page, size });
    yield put(loadRentalListSuccess(response.data));
  } catch (e) {
    console.error(e);
    yield put(loadRentalListFailure(e.response.data || e));
  }
}
