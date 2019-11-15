import { fork, put, takeLatest, call, all } from 'redux-saga/effects';

import { USER } from '@redux/actionTypes';
import {
  signUpSuccess,
  signUpFailure,
  signInSuccess,
  signInFailure,
  signOutSuccess,
  signOutFailure,
  loadUserSuccess,
  loadUserFailure,
} from '@redux/actions/userActions';
import { signUpApi, signInApi, loadUserApi, signOutApi } from '@redux/api/user';

export default function*() {
  yield all([
    fork(watchSignUpRequest),
    fork(watchSignInRequest),
    fork(watchLoadUserRequest),
    fork(watchSignOutRequest),
  ]);
}

function* watchSignUpRequest() {
  yield takeLatest(USER.SIGN_UP_REQUEST, signUp);
}
function* signUp({ user }) {
  try {
    const response = yield call(signUpApi, user);
    yield put(signUpSuccess(response));
  } catch (e) {
    yield put(signUpFailure(e));
  }
}

function* watchSignInRequest() {
  yield takeLatest(USER.SIGN_IN_REQUEST, signIn);
}
function* signIn({ user }) {
  try {
    const response = yield call(signInApi, user);
    yield put(signInSuccess(response));
  } catch (e) {
    yield put(signInFailure(e));
  }
}

function* watchSignOutRequest() {
  yield takeLatest(USER.SIGN_OUT_REQUEST, signOut);
}
function* signOut() {
  try {
    yield call(signOutApi);
    yield put(signOutSuccess());
  } catch (e) {
    yield put(signOutFailure(e));
  }
}

function* watchLoadUserRequest() {
  yield takeLatest(USER.LOAD_USER_REQUEST, loadUser);
}
function* loadUser({ id }) {
  try {
    const response = yield call(loadUserApi, id);
    yield put(loadUserSuccess(response));
  } catch (e) {
    if (id === 0) {
      yield put(loadUserFailure(e));
    }
  }
}
