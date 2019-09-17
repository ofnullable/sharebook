import { BOOK } from '@redux/actionTypes';

export const loadBookListRequest = (searchText = '', page = 1, size = 12) => ({
  type: BOOK.LOAD_BOOK_LIST_REQUEST,
  searchText,
  page,
  size,
});
export const loadBookListSuccess = data => ({
  type: BOOK.LOAD_BOOK_LIST_SUCCESS,
  data,
});
export const loadBookListFailure = error => ({
  type: BOOK.LOAD_BOOK_LIST_FAILURE,
  error,
});

export const loadBookListByCategoryRequest = (category, page = 1, size = 12) => ({
  type: BOOK.LOAD_BOOK_LIST_BY_CATEGORY_REQUEST,
  category,
  page,
  size,
});
export const loadBookListByCategorySuccess = data => ({
  type: BOOK.LOAD_BOOK_LIST_BY_CATEGORY_SUCCESS,
  data,
});
export const loadBookListByCategoryFailure = error => ({
  type: BOOK.LOAD_BOOK_LIST_BY_CATEGORY_FAILURE,
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
