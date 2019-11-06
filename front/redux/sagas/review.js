import { fork, put, takeLatest, call, all } from 'redux-saga/effects';

import { REVIEW } from '@redux/actionTypes';
import { loadReviewListApi } from '@redux/api/review';
import { loadReviewListSuccess, loadReviewListFailure } from '@redux/actions/reviewActions';

export default function*() {
  yield all([fork(watchLoadReviewListRequest)]);
}

function* watchLoadReviewListRequest() {
  yield takeLatest(REVIEW.LOAD_REVIEW_LIST_REQUEST, loadReviewList);
}
function* loadReviewList({ bookId }) {
  try {
    const response = yield call(loadReviewListApi, bookId);
    yield put(loadReviewListSuccess(response.data));
  } catch (e) {
    console.error(e);
    yield put(loadReviewListFailure(e.response.data || e));
  }
}
