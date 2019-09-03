import produce from 'immer';

import { BOOK } from '@redux/actionTypes';

const initial = {
  list: {
    data: [],
    isLoading: false,
    error: {},
  },
};

export default (state = initial, action) => {
  return produce(state, draft => {
    switch (action.type) {
      case BOOK.LOAD_BOOK_LIST_REQUEST:
        draft.list.isLoading = true;
        draft.list.error = {};
        break;
      case BOOK.LOAD_BOOK_LIST_SUCCESS:
        draft.list.data = action.data;
        draft.list.isLoading = false;
        break;
      case BOOK.LOAD_BOOK_LIST_FAILURE:
        draft.list.error = action.error;
        draft.list.isLoading = false;
        break;

      default:
        break;
    }
  });
};
