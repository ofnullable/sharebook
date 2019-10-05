import { BOOK } from '@redux/actionTypes';

export const loadBookListRequest = (searchText = '', page = 1, size = 20) => ({
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

export const loadBookListByCategoryRequest = (category, page = 1, size = 20) => ({
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

export const loadMyBookListRequest = (page = 1, size = 20) => ({
  type: BOOK.LOAD_MY_BOOK_LIST_REQUEST,
  page,
  size,
});
export const loadMyBookListSuccess = data => ({
  type: BOOK.LOAD_MY_BOOK_LIST_SUCCESS,
  data,
});
export const loadMyBookListFailure = error => ({
  type: BOOK.LOAD_MY_BOOK_LIST_FAILURE,
  error,
});

export const loadMyBookListByRentalStatusRequest = status => ({
  type: BOOK.LOAD_MY_BOOK_LIST_BY_RENTAL_STATUS_REQUEST,
  status,
});
export const loadMyBookListByRentalStatusSuccess = data => ({
  type: BOOK.LOAD_MY_BOOK_LIST_BY_RENTAL_STATUS_SUCCESS,
  data,
});
export const loadMyBookListByRentalStatusFailure = error => ({
  type: BOOK.LOAD_MY_BOOK_LIST_BY_RENTAL_STATUS_FAILURE,
  error,
});

export const changeBookStatus = (id, status) => ({
  type: BOOK.CHANGE_BOOK_STATUS,
  id,
  status,
});
