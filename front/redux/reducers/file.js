import produce from 'immer';

import { FILE } from '@redux/actionTypes';

const initial = {
  data: {
    url: '',
    isLoading: false,
    error: {},
  },
};

export default (state = initial, action) => {
  return produce(state, draft => {
    switch (action.type) {
      case FILE.UPLOAD_IMAGE_REQUEST:
        draft.data.isLoading = true;
        draft.data.url = '';
        break;
      case FILE.UPLOAD_IMAGE_SUCCESS:
        draft.data.isLoading = false;
        draft.data.url = action.data;
        break;
      case FILE.UPLOAD_IMAGE_FAILURE:
        draft.data.isLoading = false;
        draft.data.error = action.error;
        break;

      default:
        break;
    }
  });
};
