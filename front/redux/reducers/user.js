import produce from 'immer';

import { USER } from '@redux/actionTypes';

const initial = {
  user: {
    data: {},
    isSignedIn: false,
    isLoading: false,
    error: {},
  },
  join: {
    data: {},
    error: {},
    isLoading: false,
  },
};

export default (state = initial, action) => {
  return produce(state, draft => {
    switch (action.type) {
      case USER.SIGN_IN_REQUEST:
      case USER.SIGN_OUT_REQUEST:
      case USER.LOAD_USER_REQUEST:
        draft.user.error = {};
        draft.user.isLoading = true;
        break;
      case USER.SIGN_IN_FAILURE:
      case USER.SIGN_OUT_FAILURE:
      case USER.LOAD_USER_FAILURE:
        draft.user.error = action.error;
        draft.user.isLoading = false;
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

      case USER.SIGN_UP_REQUEST:
        draft.join.error = {};
        draft.join.isLoading = true;
        break;
      case USER.SIGN_UP_SUCCESS:
        draft.join.data = action.data;
        draft.join.isLoading = false;
        break;
      case USER.SIGN_UP_FAILURE:
        draft.join.error = action.error;
        draft.join.isLoading = false;
        break;

      default:
        break;
    }
  });
};
