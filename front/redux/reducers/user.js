import produce from 'immer';

import { SIGN_IN_REQUEST, SIGN_IN_SUCCESS, SIGN_IN_FAILURE } from '../actionTypes';

const initial = {
  user: {},
  isSignedIn: false,
  isProceeding: false,
  error: {},
};

export default (state = initial, action) => {
  return produce(state, draft => {
    switch (action.type) {
      case SIGN_IN_REQUEST:
        draft.isProceeding = true;
        break;
      case SIGN_IN_SUCCESS:
        draft.user = action.data;
        draft.isSignedIn = true;
        draft.isProceeding = false;
        break;
      case SIGN_IN_FAILURE:
        draft.error = action.error;
        draft.isProceeding = false;
        break;
      default:
        break;
    }
  });
};
