import produce from 'immer';

import { RENTAL } from '@redux/actionTypes';

const initial = {
  histories: {
    data: [],
    isLoading: false,
    error: {},
  },
  myRentals: {
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
      case RENTAL.RENTAL_BOOK_REQUEST:
        draft.histories.isLoading = true;
        break;
      case RENTAL.RENTAL_BOOK_SUCCESS:
        draft.histories.isLoading = false;
        draft.histories.data.push(action.data);
        break;
      case RENTAL.RENTAL_BOOK_FAILURE:
        draft.histories.isLoading = false;
        draft.histories.error = action.error;
        break;

      case RENTAL.LOAD_RENTAL_LIST_REQUEST:
        draft.myRentals.data = !action.page || action.page === 1 ? [] : draft.myRentals.data;
        draft.myRentals.page = action.page ? action.page : 1;
        draft.myRentals.isLoading = true;
        draft.myRentals.error = {};
        break;
      case RENTAL.LOAD_RENTAL_LIST_SUCCESS:
        draft.myRentals.data = draft.myRentals.data.concat(action.data.content);
        draft.myRentals.isLast = action.data.last;
        draft.myRentals.totalPages = action.data.totalPages;
        draft.myRentals.isLoading = false;
        break;
      case RENTAL.LOAD_RENTAL_LIST_FAILURE:
        draft.myRentals.isLoading = false;
        draft.myRentals.error = action.error;
        break;

      default:
        break;
    }
  });
};
