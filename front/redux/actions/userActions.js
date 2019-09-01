import { SIGN_IN_REQUEST, SIGN_IN_SUCCESS, SIGN_IN_FAILURE } from '../actionTypes';

export const signInRequest = ({ username, password }) => ({
  type: SIGN_IN_REQUEST,
  payload: {
    username,
    password,
  },
});

export const signInSuccess = () => ({
  type: SIGN_IN_SUCCESS,
});

export const signInFailure = () => ({
  type: SIGN_IN_FAILURE,
});
