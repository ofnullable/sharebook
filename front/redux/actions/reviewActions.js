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

export const saveReviewRequest = data => ({
  type: REVIEW.SAVE_REVIEW_REQUEST,
  data,
});
export const saveReviewSuccess = data => ({
  type: REVIEW.SAVE_REVIEW_SUCCESS,
  data,
});
export const saveReviewFailure = error => ({
  type: REVIEW.SAVE_REVIEW_FAILURE,
  error,
});

export const updateReviewRequest = data => ({
  type: REVIEW.UPDATE_REVIEW_REQUEST,
  data,
});
export const updateReviewSuccess = data => ({
  type: REVIEW.UPDATE_REVIEW_SUCCESS,
  data,
});
export const updateReviewFailure = error => ({
  type: REVIEW.UPDATE_REVIEW_FAILURE,
  error,
});

export const deleteReviewRequest = id => ({
  type: REVIEW.DELETE_REVIEW_REQUEST,
  id,
});
export const deleteReviewSuccess = id => ({
  type: REVIEW.DELETE_REVIEW_SUCCESS,
  id,
});
export const deleteReviewFailure = error => ({
  type: REVIEW.DELETE_REVIEW_FAILURE,
  error,
});

export const loadMyReviewListRequest = () => ({
  type: REVIEW.LOAD_MY_REVIEW_LIST_REQUEST,
});
export const loadMyReviewListSuccess = data => ({
  type: REVIEW.LOAD_MY_REVIEW_LIST_SUCCESS,
  data,
});
export const loadMyReviewListFailure = error => ({
  type: REVIEW.LOAD_MY_REVIEW_LIST_FAILURE,
  error,
});
