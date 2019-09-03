import produce from 'immer';

import { USER } from '@redux/actionTypes';

const initial = {
  user: {},
  error: {},
  isSignedIn: false,
  isLoading: false,
};

export default (state = initial, action) => {
  return produce(state, draft => {
    switch (action.type) {
      case USER.SIGN_IN_REQUEST:
      case USER.SIGN_OUT_REQUEST:
      case USER.LOAD_USER_REQUEST:
        draft.error = '';
        draft.isLoading = true;
        break;

      case USER.SIGN_IN_FAILURE:
      case USER.SIGN_OUT_FAILURE:
      case USER.LOAD_USER_FAILURE:
        draft.error = action.error;
        draft.isLoading = false;
        break;

      case USER.SIGN_IN_SUCCESS:
      case USER.LOAD_USER_SUCCESS:
        draft.user = action.data;
        draft.isSignedIn = true;
        draft.isLoading = false;
        break;

      case USER.SIGN_OUT_SUCCESS:
        draft.user = {};
        draft.isSignedIn = false;
        draft.isLoading = false;
        break;

      default:
        break;
    }
  });
};
