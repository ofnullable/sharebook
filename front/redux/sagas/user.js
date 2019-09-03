import { fork, put, takeLatest, call, all } from 'redux-saga/effects';
import { SIGN_IN_REQUEST } from '../actionTypes';
import { signInApi } from '../api/user';
import { signInSuccess, signInFailure } from '../actions/userActions';

export default function*() {
  yield all([fork(watchSignInRequest)]);
}
function* watchSignInRequest() {
  yield takeLatest(SIGN_IN_REQUEST, signIn);
}
function* signIn({ payload }) {
  try {
    const response = yield call(signInApi, payload);
    yield put(signInSuccess(response.data));
  } catch (e) {
    console.error(e);
    yield put(signInFailure(e.response.data));
  }
}
