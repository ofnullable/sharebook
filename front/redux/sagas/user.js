import { fork, put, takeLatest, call, all } from 'redux-saga/effects';
import { SIGN_IN_REQUEST } from '../actionTypes';

export default function*() {
  yield all([fork(watchSignInRequest)]);
}
function* watchSignInRequest() {
  yield takeLatest(SIGN_IN_REQUEST, signIn);
}
function* signIn(action) {
  console.log(action);
}
