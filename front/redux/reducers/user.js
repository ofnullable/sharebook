import { SIGN_IN_REQUEST, SIGN_IN_SUCCESS, SIGN_IN_FAILURE } from '../actionTypes';

const initial = {
  user: {
    id: 0,
    username: '',
  },
  isSignedIn: false,
  isProceeding: false,
  error: '',
};

export default (state = initial, action) => {
  switch (action.type) {
    case SIGN_IN_REQUEST:
      return {
        ...state,
        isProceeding: true,
      };
    case SIGN_IN_SUCCESS:
      return {
        ...state,
        isProceeding: false,
      };
    case SIGN_IN_FAILURE:
      return {
        ...state,
        isProceeding: false,
      };
    default:
      return {
        ...state,
      };
  }
};
