import produce from 'immer';

import { BOOK } from '@redux/actionTypes';
import Book from '../../pages/book';

const initial = {
  list: {
    data: [],
    isLast: false,
    isLoading: false,
    error: {},
  },
  detail: {
    data: {},
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
        console.log(action.data);
        draft.list.data = action.data.content;
        draft.list.isLast = action.data.last;
        draft.list.totalPages = action.data.totalPages;
        draft.list.isLoading = false;
        break;
      case BOOK.LOAD_BOOK_LIST_FAILURE:
        draft.list.error = action.error;
        draft.list.isLoading = false;
        break;

      case BOOK.LOAD_BOOK_REQUEST:
        draft.detail.isLoading = true;
        draft.detail.error = {};
        break;
      case BOOK.LOAD_BOOK_SUCCESS:
        draft.detail.isLoading = false;
        draft.detail.data = action.data;
        break;
      case BOOK.LOAD_BOOK_FAILURE:
        draft.detail.isLoading = false;
        draft.detail.error = action.error;
        break;

      default:
        break;
    }
  });
};
