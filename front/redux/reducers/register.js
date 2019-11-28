import produce from 'immer';

import { REGISTER } from '@redux/actionTypes';

const initial = {
  image: {
    uri: '',
    isLoading: false,
    error: {},
  },
  result: {
    id: 0,
    isLoading: false,
    error: {},
  },
};

export default (state = initial, action) => {
  return produce(state, draft => {
    switch (action.type) {
      case REGISTER.UPLOAD_IMAGE_REQUEST:
        draft.image.isLoading = true;
        draft.image.uri = '';
        break;
      case REGISTER.UPLOAD_IMAGE_SUCCESS:
        draft.image.isLoading = false;
        draft.image.uri = action.data;
        break;
      case REGISTER.UPLOAD_IMAGE_FAILURE:
        draft.image.isLoading = false;
        draft.image.error = action.error;
        break;

      case REGISTER.REGISTER_BOOK_REQUEST:
        draft.result.isLoading = true;
        draft.result.id = 0;

        break;
      case REGISTER.REGISTER_BOOK_SUCCESS:
        draft.result.isLoading = false;
        draft.result.id = action.id;

        break;
      case REGISTER.REGISTER_BOOK_FAILURE:
        draft.result.isLoading = false;
        draft.result.error = action.error;

        break;

      case REGISTER.CLEAR:
        draft.result = initial.result;
        draft.image = initial.image;
        break;

      default:
        break;
    }
  });
};
