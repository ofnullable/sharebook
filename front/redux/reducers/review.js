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
  updateRequest: {
    isLoading: false,
    error: '',
  },
  deleteRequest: {
    isLoading: false,
    error: '',
  },
};

export default (state = initial, action) => {
  return produce(state, draft => {
    switch (action.type) {
      case REVIEW.LOAD_REVIEW_LIST_REQUEST:
        draft.reviewList.isLoading = true;
        draft.saveRequest.error = '';
        draft.updateRequest.error = '';
        draft.deleteRequest.error = '';
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
        draft.saveRequest.isLoading = false;
        draft.saveRequest.error = action.error;
        break;

      case REVIEW.UPDATE_REVIEW_REQUEST:
        draft.updateRequest.error = '';
        draft.updateRequest.isLoading = true;
        break;
      case REVIEW.UPDATE_REVIEW_SUCCESS: {
        draft.updateRequest.isLoading = false;
        const index = draft.reviewList.data.findIndex(r => r.id === action.data.id);
        draft.reviewList.data[index] = action.data;
        break;
      }
      case REVIEW.UPDATE_REVIEW_FAILURE:
        draft.updateRequest.isLoading = false;
        draft.updateRequest.error = action.error;
        break;

      case REVIEW.DELETE_REVIEW_REQUEST:
        draft.deleteRequest.error = '';
        draft.deleteRequest.isLoading = true;
        break;
      case REVIEW.DELETE_REVIEW_SUCCESS: {
        draft.deleteRequest.isLoading = false;
        const target = draft.reviewList.data.findIndex(r => r.id === action.id);
        draft.reviewList.data.splice(target, 1);
        break;
      }
      case REVIEW.DELETE_REVIEW_FAILURE:
        draft.deleteRequest.isLoading = false;
        draft.deleteRequest.error = action.error;
        break;

      default:
        break;
    }
  });
};
