import { fork, put, takeLatest, call, all } from 'redux-saga/effects';

import { REVIEW } from '@redux/actionTypes';
import {
  loadReviewListApi,
  saveReviewApi,
  updateReviewApi,
  deleteReviewApi,
  loadMyReviewListApi,
} from '@redux/api/review';
import {
  loadReviewListSuccess,
  loadReviewListFailure,
  saveReviewSuccess,
  saveReviewFailure,
  updateReviewSuccess,
  updateReviewFailure,
  deleteReviewSuccess,
  deleteReviewFailure,
  loadMyReviewListSuccess,
  loadMyReviewListFailure,
} from '@redux/actions/reviewActions';

export default function*() {
  yield all([
    fork(watchLoadReviewListRequest),
    fork(watchSaveReviewRequest),
    fork(watchUpdateReviewRequest),
    fork(watchDeleteReviewRequest),
    fork(watchLoadMyReviewListRequest),
  ]);
}

function* watchLoadReviewListRequest() {
  yield takeLatest(REVIEW.LOAD_REVIEW_LIST_REQUEST, loadReviewList);
}
function* loadReviewList({ bookId }) {
  try {
    const response = yield call(loadReviewListApi, bookId);
    yield put(loadReviewListSuccess(response));
  } catch (e) {
    yield put(loadReviewListFailure(e));
  }
}

function* watchSaveReviewRequest() {
  yield takeLatest(REVIEW.SAVE_REVIEW_REQUEST, saveReview);
}
function* saveReview({ data }) {
  try {
    const response = yield call(saveReviewApi, data);
    yield put(saveReviewSuccess(response));
  } catch (e) {
    yield put(saveReviewFailure(e));
  }
}

function* watchUpdateReviewRequest() {
  yield takeLatest(REVIEW.UPDATE_REVIEW_REQUEST, updateReview);
}
function* updateReview({ data }) {
  try {
    const response = yield call(updateReviewApi, data);
    yield put(updateReviewSuccess(response));
  } catch (e) {
    yield put(updateReviewFailure(e));
  }
}

function* watchDeleteReviewRequest() {
  yield takeLatest(REVIEW.DELETE_REVIEW_REQUEST, deleteReview);
}
function* deleteReview({ id }) {
  try {
    const response = yield call(deleteReviewApi, id);
    yield put(deleteReviewSuccess(response));
  } catch (e) {
    yield put(deleteReviewFailure(e));
  }
}

function* watchLoadMyReviewListRequest() {
  yield takeLatest(REVIEW.LOAD_MY_REVIEW_LIST_REQUEST, loadMyReviewList);
}
function* loadMyReviewList() {
  try {
    const response = yield call(loadMyReviewListApi);
    yield put(loadMyReviewListSuccess(response));
  } catch (e) {
    yield put(loadMyReviewListFailure(e));
  }
}
