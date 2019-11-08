import { fork, put, takeLatest, call, all } from 'redux-saga/effects';

import { REVIEW } from '@redux/actionTypes';
import { loadReviewListApi, saveReviewApi } from '@redux/api/review';
import {
  loadReviewListSuccess,
  loadReviewListFailure,
  saveReviewSuccess,
  saveReviewFailure,
} from '@redux/actions/reviewActions';

export default function*() {
  yield all([fork(watchLoadReviewListRequest), fork(watchSaveReviewRequest)]);
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
    yield put(loadReviewListFailure(e));
  }
}

function* watchSaveReviewRequest() {
  yield takeLatest(REVIEW.SAVE_REVIEW_REQUEST, saveReview);
}
function* saveReview({ data }) {
  try {
    const response = yield call(saveReviewApi, data);
    yield put(saveReviewSuccess(response.data));
  } catch (e) {
    console.error(e);
    yield put(saveReviewFailure(e));
  }
}
