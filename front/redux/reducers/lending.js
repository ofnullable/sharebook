import produce from 'immer';

import { LENDING } from '@redux/actionTypes';

const initial = {
  latestLending: {
    data: {},
    isLoading: false,
    error: {},
  },
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

const getTarget = state => {
  return state.histories.data.length ? state.histories : state.myLendings;
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

      case LENDING.ACCEPT_LENDING_REQUEST:
      case LENDING.REJECT_LENDING_REQUEST:
        draft.latestLending.isLoading = true;
        break;
      case LENDING.ACCEPT_LENDING_SUCCESS:
      case LENDING.REJECT_LENDING_SUCCESS:
        draft.latestLending.isLoading = false;
        draft.latestLending.data = action.data;
        break;
      case LENDING.ACCEPT_LENDING_FAILURE:
      case LENDING.REJECT_LENDING_FAILURE:
        draft.latestLending.isLoading = false;
        draft.latestLending.error = action.data;
        break;

      case LENDING.CANCEL_BORROW_REQUEST:
      case LENDING.RETURN_BOOK_REQUEST: {
        const target = getTarget(draft);
        target.isLoading = true;
        break;
      }
      case LENDING.CANCEL_BORROW_SUCCESS:
      case LENDING.RETURN_BOOK_SUCCESS: {
        const target = getTarget(draft);
        target.isLoading = false;
        const originIndex = target.data.findIndex(d => d.id === action.data.id);
        if (originIndex > -1) target.data.splice(originIndex, 1);
        break;
      }
      case LENDING.CANCEL_BORROW_FAILURE:
      case LENDING.RETURN_BOOK_FAILURE: {
        const target = getTarget(draft);
        target.isLoading = false;
        break;
      }

      case LENDING.LOAD_LATEST_LENDING_REQUEST:
        draft.latestLending.isLoading = true;
        break;
      case LENDING.LOAD_LATEST_LENDING_SUCCESS:
        draft.latestLending.isLoading = false;
        draft.latestLending.data = action.data;
        break;
      case LENDING.LOAD_LATEST_LENDING_FAILURE:
        draft.latestLending.isLoading = false;
        draft.latestLending.error = action.error;
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
