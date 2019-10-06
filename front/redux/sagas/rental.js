import { fork, put, takeLatest, call, all } from 'redux-saga/effects';

import { BOOK_STATUS } from '@utils/consts';
import { RENTAL } from '@redux/actionTypes';
import { loadMyRentalInfoApi, loadRentalInfoByBookIdApi, rentalBookApi } from '@redux/api/rental';
import {
  loadMyRentalInfoSuccess,
  loadMyRentalInfoFailure,
  loadRentalInfoByBookIdSuccess,
  loadRentalInfoByBookIdFailure,
  rentalBookSuccess,
  rentalBookFailure,
} from '@redux/actions/rentalActions';
import { changeBookStatus } from '@redux/actions/bookActions';

export default function*() {
  yield all([fork(watchRentalBookRequest)]);
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
