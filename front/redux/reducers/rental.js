import produce from 'immer';

import { RENTAL } from '@redux/actionTypes';

const initial = {
  histories: {
    data: [],
    isLoading: false,
  },
  historiesByBook: {
    data: [],
    isLoading: false,
  },
};

export default (state = initial, action) => {
  return produce(state, draft => {
    switch (action.type) {
      case RENTAL.LOAD_MY_RENTAL_INFO_REQUEST:
        console.log(action);
        break;
      case RENTAL.LOAD_MY_RENTAL_INFO_SUCCESS:
        console.log(action);
        break;
      case RENTAL.LOAD_MY_RENTAL_INFO_FAILURE:
        console.log(action);
        break;

      case RENTAL.LOAD_MY_RENTAL_INFO_BY_BOOK_ID_REQUEST:
        console.log(action);
        break;
      case RENTAL.LOAD_MY_RENTAL_INFO_BY_BOOK_ID_SUCCESS:
        draft.historiesByBook.data = action.data;
        console.log(action);
        break;
      case RENTAL.LOAD_MY_RENTAL_INFO_BY_BOOK_ID_FAILURE:
        console.log(action);
        break;

      case RENTAL.RENTAL_BOOK_REQUEST:
        console.log(action);
        break;
      case RENTAL.RENTAL_BOOK_SUCCESS:
        draft.histories.data.push(action.data);
        console.log(action);
        break;
      case RENTAL.RENTAL_BOOK_FAILURE:
        console.log(action);
        break;
      default:
        break;
    }
  });
};
