import { fork, put, takeLatest, call, all } from 'redux-saga/effects';
import { SIGN_IN_REQUEST } from '../actionTypes';
import { signInApi } from '../api/user';

export default function*() {
  yield all([fork(watchSignInRequest)]);
}
function* watchSignInRequest() {
  yield takeLatest(SIGN_IN_REQUEST, signIn);
}
function* signIn({ payload }) {
  try {
    console.log(payload);
    const response = yield call(signInApi, payload);
    console.log('response', response);
  } catch (e) {}
}
