import produce from 'immer';

import { LENDING } from '@redux/actionTypes';

const initial = {
  histories: {
    data: [],
    isLoading: false,
    error: {},
  },
  myLendings: {
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
      case LENDING.BORROW_BOOK_REQUEST:
        draft.histories.isLoading = true;
        break;
      case LENDING.BORROW_BOOK_SUCCESS:
        draft.histories.isLoading = false;
        draft.histories.data.push(action.data);
        break;
      case LENDING.BORROW_BOOK_FAILURE:
        draft.histories.isLoading = false;
        draft.histories.error = action.error;
        break;

      case LENDING.LOAD_LENDING_LIST_REQUEST:
        draft.myLendings.data = !action.page || action.page === 1 ? [] : draft.myLendings.data;
        draft.myLendings.page = action.page ? action.page : 1;
        draft.myLendings.isLoading = true;
        draft.myLendings.error = {};
        break;
      case LENDING.LOAD_LENDING_LIST_SUCCESS:
        draft.myLendings.data = draft.myLendings.data.concat(action.data.content);
        draft.myLendings.isLast = action.data.last;
        draft.myLendings.totalPages = action.data.totalPages;
        draft.myLendings.isLoading = false;
        break;
      case LENDING.LOAD_LENDING_LIST_FAILURE:
        draft.myLendings.isLoading = false;
        draft.myLendings.error = action.error;
        break;

      default:
        break;
    }
  });
};
