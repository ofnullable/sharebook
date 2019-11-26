import { fork, put, takeLatest, call, all } from 'redux-saga/effects';

import { USER } from '@redux/actionTypes';
import * as actions from '@redux/actions/userActions';
import * as API from '@redux/api/user';

export default function*() {
  yield all([
    fork(watchEmailDuplicationCheckRequest),
    fork(watchSignUpRequest),
    fork(watchSignInRequest),
    fork(watchLoadUserRequest),
    fork(watchSignOutRequest),
    fork(watchPasswordVerifyRequest),
    fork(watchUpdateAvatarRequest),
    fork(watchUpdateInfoRequest),
  ]);
}

function* watchEmailDuplicationCheckRequest() {
  yield takeLatest(USER.EMAIL_DUPLICATION_CHECK_REQUEST, emailDuplicationCheck);
}
function* emailDuplicationCheck({ email }) {
  try {
    const response = yield call(API.emailDuplicationCheckApi, email);
    yield put(actions.emailDuplicationCheckSuccess(response));
  } catch (e) {
    yield put(actions.emailDuplicationCheckFailure(e));
  }
}

function* watchSignUpRequest() {
  yield takeLatest(USER.SIGN_UP_REQUEST, signUp);
}
function* signUp({ user }) {
  try {
    const response = yield call(API.signUpApi, user);
    yield put(actions.signUpSuccess(response));
  } catch (e) {
    yield put(actions.signUpFailure(e));
  }
}

function* watchSignInRequest() {
  yield takeLatest(USER.SIGN_IN_REQUEST, signIn);
}
function* signIn({ user }) {
  try {
    const response = yield call(API.signInApi, user);
    yield put(actions.signInSuccess(response));
  } catch (e) {
    yield put(actions.signInFailure(e));
  }
}

function* watchSignOutRequest() {
  yield takeLatest(USER.SIGN_OUT_REQUEST, signOut);
}
function* signOut() {
  try {
    yield call(API.signOutApi);
    yield put(actions.signOutSuccess());
  } catch (e) {
    yield put(actions.signOutFailure(e));
  }
}

function* watchLoadUserRequest() {
  yield takeLatest(USER.LOAD_USER_REQUEST, loadUser);
}
function* loadUser({ id }) {
  try {
    const response = yield call(API.loadUserApi, id);
    yield put(actions.loadUserSuccess(response));
  } catch (e) {
    yield put(actions.loadUserFailure(e));
  }
}

function* watchPasswordVerifyRequest() {
  yield takeLatest(USER.PASSWORD_VERIFY_REQUEST, passwordVerify);
}
function* passwordVerify({ password }) {
  try {
    const response = yield call(API.passwordVerifyApi, password);
    yield put(actions.passwordVerifySuccess(response));
  } catch (e) {
    yield put(actions.passwordVerifyFailure(e));
  }
}

function* watchUpdateAvatarRequest() {
  yield takeLatest(USER.UPDATE_AVATAR_REQUEST, updateAvatar);
}
function* updateAvatar({ file }) {
  try {
    const response = yield call(API.updateAvatarApi, file);
    yield put(actions.updateAvatarSuccess(response));
  } catch (e) {
    yield put(actions.updateAvatarFailure(e));
  }
}

function* watchUpdateInfoRequest() {
  yield takeLatest(USER.UPDATE_INFO_REQUEST, updateInfo);
}
function* updateInfo({ data }) {
  try {
    const response = yield call(API.updateInfoApi, data);
    yield put(actions.updateInfoSuccess(response));
  } catch (e) {
    yield put(actions.updateInfoFailure(e));
  }
}
