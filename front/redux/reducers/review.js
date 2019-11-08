import produce from 'immer';

import { REVIEW } from '@redux/actionTypes';

const initial = {
  reviewList: {
    data: {},
    isLoading: false,
    error: {},
  },
  saveRequest: {
    isLoading: false,
    error: '',
  },
};

export default (state = initial, action) => {
  return produce(state, draft => {
    switch (action.type) {
      case REVIEW.LOAD_REVIEW_LIST_REQUEST:
        draft.reviewList.isLoading = true;
        break;
      case REVIEW.LOAD_REVIEW_LIST_SUCCESS:
        draft.reviewList.isLoading = false;
        draft.reviewList.data = action.data;
        break;
      case REVIEW.LOAD_REVIEW_LIST_FAILURE:
        draft.reviewList.isLoading = false;
        draft.reviewList.error = action.error;
        break;

      case REVIEW.SAVE_REVIEW_REQUEST:
        draft.saveRequest.error = '';
        draft.saveRequest.isLoading = true;
        break;
      case REVIEW.SAVE_REVIEW_SUCCESS:
        draft.reviewList.data.unshift(action.data);
        draft.saveRequest.isLoading = false;
        break;
      case REVIEW.SAVE_REVIEW_FAILURE:
        draft.saveRequest.error = action.error;
        draft.saveRequest.isLoading = false;
        break;

      default:
        break;
    }
  });
};
