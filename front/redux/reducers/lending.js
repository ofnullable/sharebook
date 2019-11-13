import produce from 'immer';

import { LENDING } from '@redux/actionTypes';

const initial = {
  latestLending: {
    data: {},
    isLoading: false,
    error: {},
  },
  myRequests: {
    data: [],
    page: 1,
    totalPages: 1,
    isLast: false,
    isLoading: false,
    error: {},
  },
  requests: {
    data: [],
    page: 1,
    totalPages: 1,
    isLast: false,
    isLoading: false,
    error: {},
  },
};

const getTarget = (state, targetName) => {
  if (!targetName) {
    return [state.latestLending, state.myRequests, state.requests].filter(
      attr => attr.isLoading
    )[0];
  }
  return state[targetName];
};

export default (state = initial, action) => {
  return produce(state, draft => {
    switch (action.type) {
      case LENDING.BORROW_BOOK_REQUEST:
        draft.latestLending.isLoading = true;
        break;
      case LENDING.BORROW_BOOK_SUCCESS:
        draft.latestLending.isLoading = false;
        draft.latestLending.data = action.data;
        break;
      case LENDING.BORROW_BOOK_FAILURE:
        draft.latestLending.isLoading = false;
        draft.latestLending.error = action.error;
        break;

      case LENDING.ACCEPT_LENDING_REQUEST:
      case LENDING.REJECT_LENDING_REQUEST: {
        const target = getTarget(draft, action.target);
        target.isLoading = true;
        target.error = {};
        break;
      }
      case LENDING.ACCEPT_LENDING_SUCCESS:
      case LENDING.REJECT_LENDING_SUCCESS: {
        const target = getTarget(draft);
        target.isLoading = false;
        const originIndex = target.data.findIndex(d => d.id === action.data.id);
        if (originIndex > -1) target.data.splice(originIndex, 1);
        break;
      }
      case LENDING.ACCEPT_LENDING_FAILURE:
      case LENDING.REJECT_LENDING_FAILURE: {
        const target = getTarget(draft);
        target.isLoading = false;
        target.error = action.error;
        break;
      }

      case LENDING.CANCEL_BORROW_REQUEST:
      case LENDING.RETURN_BOOK_REQUEST: {
        const target = getTarget(draft, action.target);
        target.isLoading = true;
        target.error = {};
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
        target.error = action.error;
        break;
      }

      case LENDING.LOAD_LATEST_LENDING_REQUEST:
        draft.latestLending.isLoading = true;
        draft.latestLending.data = {};
        draft.latestLending.error = {};
        break;
      case LENDING.LOAD_LATEST_LENDING_SUCCESS:
        draft.latestLending.isLoading = false;
        draft.latestLending.data = action.data;
        break;
      case LENDING.LOAD_LATEST_LENDING_FAILURE:
        draft.latestLending.isLoading = false;
        draft.latestLending.error = action.error;
        break;

      case LENDING.LOAD_MY_REQUEST_LIST_BY_STATUS_REQUEST:
        draft.myRequests.data = !action.page || action.page === 1 ? [] : draft.myRequests.data;
        draft.myRequests.page = action.page ? action.page : 1;
        draft.myRequests.isLoading = true;
        draft.myRequests.error = {};
        break;
      case LENDING.LOAD_MY_REQUEST_LIST_BY_STATUS_SUCCESS:
        draft.myRequests.data = draft.myRequests.data.concat(action.data.content);
        draft.myRequests.isLast = action.data.last;
        draft.myRequests.totalPages = action.data.totalPages;
        draft.myRequests.isLoading = false;
        break;
      case LENDING.LOAD_MY_REQUEST_LIST_BY_STATUS_FAILURE:
        draft.myRequests.isLoading = false;
        draft.myRequests.error = action.error;
        break;

      case LENDING.LOAD_REQUEST_LIST_FOR_MY_BOOK_REQUEST:
        draft.requests.data = !action.page || action.page === 1 ? [] : draft.requests.data;
        draft.requests.page = action.page ? action.page : 1;
        draft.requests.isLoading = true;
        draft.requests.error = {};
        break;
      case LENDING.LOAD_REQUEST_LIST_FOR_MY_BOOK_SUCCESS:
        draft.requests.data = draft.requests.data.concat(action.data.content);
        draft.requests.isLast = action.data.last;
        draft.requests.totalPages = action.data.totalPages;
        draft.requests.isLoading = false;
        break;
      case LENDING.LOAD_REQUEST_LIST_FOR_MY_BOOK_FAILURE:
        draft.requests.isLoading = false;
        draft.requests.error = action.error;
        break;

      default:
        break;
    }
  });
};
