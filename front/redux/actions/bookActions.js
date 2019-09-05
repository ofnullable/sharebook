import { BOOK } from '@redux/actionTypes';

export const loadBookListRequest = (page = 1, size = 10, searchText = '') => ({
  type: BOOK.LOAD_BOOK_LIST_REQUEST,
  page,
  size,
  searchText,
});

export const loadBookListSuccess = (data, isLast) => ({
  type: BOOK.LOAD_BOOK_LIST_SUCCESS,
  data,
  isLast,
});

export const loadBookListFailure = error => ({
  type: BOOK.LOAD_BOOK_LIST_FAILURE,
  error,
});

export const loadBookRequest = id => ({
  type: BOOK.LOAD_BOOK_REQUEST,
  id,
});

export const loadBookSuccess = data => ({
  type: BOOK.LOAD_BOOK_SUCCESS,
  data,
});

export const loadBookFailure = error => ({
  type: BOOK.LOAD_BOOK_FAILURE,
  error,
});
