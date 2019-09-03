import { BOOK } from '@redux/actionTypes';

export const loadBookListRequest = () => ({
  type: BOOK.LOAD_BOOK_LIST_REQUEST,
});

export const loadBookListSuccess = data => ({
  type: BOOK.LOAD_BOOK_LIST_SUCCESS,
  data,
});

export const loadBookListFailure = error => ({
  type: BOOK.LOAD_BOOK_LIST_FAILURE,
  error,
});
