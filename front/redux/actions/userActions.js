import { USER } from '@redux/actionTypes';

export const signUpRequest = ({ email, name, password }) => ({
  type: USER.SIGN_UP_REQUEST,
  user: {
    email,
    name,
    password,
  },
});
export const signUpSuccess = data => ({
  type: USER.SIGN_UP_SUCCESS,
  data,
});
export const signUpFailure = error => ({
  type: USER.SIGN_UP_FAILURE,
  error,
});

export const signInRequest = ({ username, password }) => ({
  type: USER.SIGN_IN_REQUEST,
  user: {
    username: username.trim().toLowerCase(),
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
