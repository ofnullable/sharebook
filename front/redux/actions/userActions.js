import { USER } from '@redux/actionTypes';

export const emailDuplicationCheckRequest = email => ({
  type: USER.EMAIL_DUPLICATION_CHECK_REQUEST,
  email,
});
export const emailDuplicationCheckSuccess = data => ({
  type: USER.EMAIL_DUPLICATION_CHECK_SUCCESS,
  data,
});
export const emailDuplicationCheckFailure = error => ({
  type: USER.EMAIL_DUPLICATION_CHECK_FAILURE,
  error,
});

export const signUpRequest = data => ({
  type: USER.SIGN_UP_REQUEST,
  user: data,
});
export const signUpSuccess = data => ({
  type: USER.SIGN_UP_SUCCESS,
  data,
});
export const signUpFailure = error => ({
  type: USER.SIGN_UP_FAILURE,
  error,
});

export const signInRequest = data => ({
  type: USER.SIGN_IN_REQUEST,
  user: data,
});
export const signInSuccess = data => ({
  type: USER.SIGN_IN_SUCCESS,
  data,
});
export const signInFailure = error => ({
  type: USER.SIGN_IN_FAILURE,
  error,
});

export const signOutRequest = () => ({
  type: USER.SIGN_OUT_REQUEST,
});
export const signOutSuccess = () => ({
  type: USER.SIGN_OUT_SUCCESS,
});
export const signOutFailure = () => ({
  type: USER.SIGN_OUT_FAILURE,
});

export const loadUserRequest = (id = 0) => ({
  type: USER.LOAD_USER_REQUEST,
  id,
});
export const loadUserSuccess = data => ({
  type: USER.LOAD_USER_SUCCESS,
  data,
});
export const loadUserFailure = error => ({
  type: USER.LOAD_USER_FAILURE,
  error,
});

export const passwordVerifyRequest = password => ({
  type: USER.PASSWORD_VERIFY_REQUEST,
  password,
});
export const passwordVerifySuccess = data => ({
  type: USER.PASSWORD_VERIFY_SUCCESS,
  data,
});
export const passwordVerifyFailure = error => ({
  type: USER.PASSWORD_VERIFY_FAILURE,
  error,
});

export const updateInfoRequest = data => ({
  type: USER.UPDATE_INFO_REQUEST,
  data,
});
export const updateInfoSuccess = data => ({
  type: USER.UPDATE_INFO_SUCCESS,
  data,
});
export const updateInfoFailure = error => ({
  type: USER.UPDATE_INFO_FAILURE,
  error,
});
