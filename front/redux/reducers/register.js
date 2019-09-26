import produce from 'immer';

import { REGISTER } from '@redux/actionTypes';

const initial = {
  image: {
    url: '',
    isLoading: false,
    error: {},
  },
  result: {
    data: {},
    isLoading: false,
    error: {},
  },
};

export default (state = initial, action) => {
  return produce(state, draft => {
    switch (action.type) {
      case REGISTER.UPLOAD_IMAGE_REQUEST:
        draft.image.isLoading = true;
        draft.image.url = '';
        break;
      case REGISTER.UPLOAD_IMAGE_SUCCESS:
        draft.image.isLoading = false;
        draft.image.url = action.data;
        break;
      case REGISTER.UPLOAD_IMAGE_FAILURE:
        draft.image.isLoading = false;
        draft.image.error = action.error;
        break;

      case REGISTER.REGISTER_BOOK_REQUEST:
        draft.result.isLoading = true;
        draft.result.data = {};
        break;
      case REGISTER.REGISTER_BOOK_SUCCESS:
        draft.result.isLoading = false;
        draft.result.data = action.data;
        break;
      case REGISTER.REGISTER_BOOK_FAILURE:
        draft.result.isLoading = false;
        draft.result.error = action.error;
        break;

      default:
        break;
    }
  });
};
