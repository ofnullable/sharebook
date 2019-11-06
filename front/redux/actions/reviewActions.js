import { REVIEW } from '@redux/actionTypes';

export const loadReviewListRequest = bookId => ({
  type: REVIEW.LOAD_REVIEW_LIST_REQUEST,
  bookId,
});

export const loadReviewListSuccess = data => ({
  type: REVIEW.LOAD_REVIEW_LIST_SUCCESS,
  data,
});

export const loadReviewListFailure = error => ({
  type: REVIEW.LOAD_REVIEW_LIST_FAILURE,
  error,
});
