import { USER } from '@redux/actionTypes';

export const signInRequest = ({ username, password }) => ({
  type: USER.SIGN_IN_REQUEST,
  payload: {
    username,
    password,
  },
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
