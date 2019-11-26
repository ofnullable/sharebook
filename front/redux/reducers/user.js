import produce from 'immer';

import { USER } from '@redux/actionTypes';

const initial = {
  user: {
    data: {},
    isSignedIn: false,
    isLoading: false,
    error: {},
    signInError: false,
  },
  join: {
    error: {},
    isLoading: false,
    isDuplicated: true,
  },
};

export default (state = initial, action) => {
  return produce(state, draft => {
    switch (action.type) {
      case USER.EMAIL_DUPLICATION_CHECK_REQUEST:
      case USER.SIGN_UP_REQUEST:
        draft.join.error = {};
        draft.join.isLoading = true;
        break;
      case USER.EMAIL_DUPLICATION_CHECK_SUCCESS:
        draft.join.isDuplicated = action.data;
        draft.join.isLoading = false;
        break;
      case USER.SIGN_UP_SUCCESS:
        draft.user.data = action.data;
        draft.user.isSignedIn = true;
        draft.join.isLoading = false;
        break;
      case USER.EMAIL_DUPLICATION_CHECK_FAILURE:
      case USER.SIGN_UP_FAILURE:
        draft.join.error = action.error;
        draft.join.isLoading = false;
        break;

      case USER.SIGN_IN_REQUEST:
      case USER.SIGN_OUT_REQUEST:
      case USER.LOAD_USER_REQUEST:
        draft.user.error = {};
        draft.user.isLoading = true;
        draft.user.signInError = false;
        break;

      case USER.SIGN_IN_SUCCESS:
      case USER.LOAD_USER_SUCCESS:
        draft.user.data = action.data;
        draft.user.isSignedIn = true;
        draft.user.isLoading = false;
        break;
      case USER.SIGN_OUT_SUCCESS:
        draft.user.data = {};
        draft.user.isSignedIn = false;
        draft.user.isLoading = false;
        break;

      case USER.SIGN_IN_FAILURE:
        draft.user.signInError = true;
      case USER.LOAD_USER_FAILURE:
        draft.user.error = action.error;
        draft.user.data = {};
        draft.user.isSignedIn = false;
        draft.user.isLoading = false;
        break;
      case USER.SIGN_OUT_FAILURE:
        draft.user.error = action.error;
        draft.user.isLoading = false;
        break;

      case USER.PASSWORD_VERIFY_REQUEST:
        draft.user.isLoading = true;
        draft.user.error = {};
        break;
      case USER.PASSWORD_VERIFY_SUCCESS:
        draft.user.isLoading = false;
        draft.user.data = action.data;
        break;
      case USER.PASSWORD_VERIFY_FAILURE:
        draft.user.isLoading = false;
        draft.user.error = action.error;
        break;

      case USER.UPDATE_AVATAR_REQUEST:
        draft.user.isLoading = true;
        draft.user.error = {};
        break;
      case USER.UPDATE_AVATAR_SUCCESS:
        draft.user.isLoading = false;
        draft.user.data = action.data;
        break;
      case USER.UPDATE_AVATAR_FAILURE:
        draft.user.isLoading = false;
        draft.user.error = action.error;
        break;

      case USER.UPDATE_INFO_REQUEST:
        draft.user.isLoading = true;
        draft.user.error = {};
        break;
      case USER.UPDATE_INFO_SUCCESS:
        draft.user.isLoading = false;
        draft.user.data = action.data;
        break;
      case USER.UPDATE_INFO_FAILURE:
        draft.user.isLoading = false;
        draft.user.error = action.error;
        break;

      default:
        break;
    }
  });
};
