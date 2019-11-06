import produce from 'immer';

import { REVIEW } from '@redux/actionTypes';

const initial = {
  reviewList: {
    data: {},
    isLoading: false,
    error: {},
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

      default:
        break;
    }
  });
};
