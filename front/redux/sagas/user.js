import { fork, put, takeLatest, call, all } from 'redux-saga/effects';

import { USER } from '@redux/actionTypes';
import {
  signInSuccess,
  signInFailure,
  signOutSuccess,
  signOutFailure,
  loadUserSuccess,
  loadUserFailure,
} from '@redux/actions/userActions';
import { signInApi, loadUserApi, signOutApi } from '@redux/api/user';

export default function*() {
  yield all([fork(watchSignInRequest), fork(watchLoadUserRequest), fork(watchSignOutRequest)]);
}

function* watchSignInRequest() {
  yield takeLatest(USER.SIGN_IN_REQUEST, signIn);
}
function* signIn({ payload }) {
  try {
    const response = yield call(signInApi, payload);
    yield put(signInSuccess(response.data));
  } catch (e) {
    console.error(e);
    yield put(signInFailure((e.response && e.response.data) || e.response));
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
    console.error(e);
    yield put(signOutFailure((e.response && e.response.data) || e.response));
  }
}

function* watchLoadUserRequest() {
  yield takeLatest(USER.LOAD_USER_REQUEST, loadUser);
}
function* loadUser({ id }) {
  try {
    const response = yield call(loadUserApi, id);
    yield put(loadUserSuccess(response.data));
  } catch (e) {
    if (id !== 0) {
      console.error(e);
    }
    yield put(loadUserFailure((e.response && e.response.data) || e.response));
  }
}
