import produce from 'immer';

import { RENTAL } from '@redux/actionTypes';

const initial = {
  histories: {
    data: [],
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

      default:
        break;
    }
  });
};
