import produce from 'immer';

import { BOOK } from '@redux/actionTypes';

const initial = {
  list: {
    data: [],
    page: 1,
    totalPages: 1,
    isLast: false,
    isLoading: false,
    error: {},
  },
  detail: {
    data: {},
    isLoading: false,
    error: {},
  },
  myBookList: {
    data: [],
    page: 1,
    totalPages: 1,
    isLast: false,
    isLoading: false,
    error: {},
  },
};

export default (state = initial, action) => {
  return produce(state, draft => {
    switch (action.type) {
      case BOOK.LOAD_BOOK_LIST_REQUEST:
      case BOOK.LOAD_BOOK_LIST_BY_CATEGORY_REQUEST:
        draft.list.data = !action.page || action.page === 1 ? [] : draft.list.data;
        draft.list.page = action.page ? action.page : 1;
        draft.list.isLoading = true;
        draft.list.error = {};
        break;
      case BOOK.LOAD_BOOK_LIST_SUCCESS:
      case BOOK.LOAD_BOOK_LIST_BY_CATEGORY_SUCCESS:
        draft.list.data = draft.list.data.concat(action.data.content);
        draft.list.isLast = action.data.last;
        draft.list.totalPages = action.data.totalPages;
        draft.list.isLoading = false;
        break;
      case BOOK.LOAD_BOOK_LIST_FAILURE:
      case BOOK.LOAD_BOOK_LIST_BY_CATEGORY_FAILURE:
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

      case BOOK.CHANGE_BOOK_STATUS:
        if (draft.detail.data.id === action.id) {
          draft.detail.data.status = action.status;
        }

      default:
        break;
    }
  });
};
