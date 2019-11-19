import { fork, put, takeLatest, call, all } from 'redux-saga/effects';

import { USER } from '@redux/actionTypes';
import {
  emailDuplicationCheckSuccess,
  emailDuplicationCheckFailure,
  signUpSuccess,
  signUpFailure,
  signInSuccess,
  signInFailure,
  signOutSuccess,
  signOutFailure,
  loadUserSuccess,
  loadUserFailure,
  passwordVerifySuccess,
  passwordVerifyFailure,
  updateInfoSuccess,
  updateInfoFailure,
} from '@redux/actions/userActions';
import {
  emailDuplicationCheckApi,
  signUpApi,
  signInApi,
  loadUserApi,
  signOutApi,
  passwordVerifyApi,
  updateInfoApi,
} from '@redux/api/user';

export default function*() {
  yield all([
    fork(watchEmailDuplicationCheckRequest),
    fork(watchSignUpRequest),
    fork(watchSignInRequest),
    fork(watchLoadUserRequest),
    fork(watchSignOutRequest),
    fork(watchPasswordVerifyRequest),
    fork(watchUpdateInfoRequest),
  ]);
}

function* watchEmailDuplicationCheckRequest() {
  yield takeLatest(USER.EMAIL_DUPLICATION_CHECK_REQUEST, emailDuplicationCheck);
}
function* emailDuplicationCheck({ email }) {
  try {
    const response = yield call(emailDuplicationCheckApi, email);
    yield put(emailDuplicationCheckSuccess(response));
  } catch (e) {
    yield put(emailDuplicationCheckFailure(e));
  }
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
    yield put(loadUserFailure(e));
  }
}

function* watchPasswordVerifyRequest() {
  yield takeLatest(USER.PASSWORD_VERIFY_REQUEST, passwordVerify);
}
function* passwordVerify({ password }) {
  try {
    const response = yield call(passwordVerifyApi, password);
    yield put(passwordVerifySuccess(response));
  } catch (e) {
    yield put(passwordVerifyFailure(e));
  }
}

function* watchUpdateInfoRequest() {
  yield takeLatest(USER.UPDATE_INFO_REQUEST, updateInfo);
}
function* updateInfo({ data }) {
  try {
    const response = yield call(updateInfoApi, data);
    yield put(updateInfoSuccess(response));
  } catch (e) {
    yield put(updateInfoFailure(e));
  }
}
