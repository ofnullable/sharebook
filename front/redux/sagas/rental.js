import { fork, put, takeLatest, call, all } from 'redux-saga/effects';

import { RENTAL } from '@redux/actionTypes';
import { loadRentalInfoApi, rentalBookApi } from '@redux/api/rental';
import {
  loadRentalInfoSuccess,
  loadRentalInfoFailure,
  rentalBookSuccess,
  rentalBookFailure,
} from '@redux/actions/rentalActions';

export default function*() {
  yield all([fork(watchLoadRentalInfoRequest), fork(watchRentalBookRequest)]);
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
    yield put(loadRentalInfoFailure((e.response && e.response.data) || e));
  }
}

function* watchRentalBookRequest() {
  yield takeLatest(RENTAL.RENTAL_BOOK_REQUEST, rentalBook);
}
function* rentalBook({ id }) {
  try {
    const response = yield call(rentalBookApi, id);
    yield put(rentalBookSuccess(response.data));
  } catch (e) {
    console.error(e);
    yield put(rentalBookFailure((e.response && e.response.data) || e));
  }
}
