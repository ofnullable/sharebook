import { LENDING } from '@redux/actionTypes';

export const borrowBookRequest = id => ({
  type: LENDING.BORROW_BOOK_REQUEST,
  id,
});
export const borrowBookSuccess = data => ({
  type: LENDING.BORROW_BOOK_SUCCESS,
  data,
});
export const borrowBookFailure = error => ({
  type: LENDING.BORROW_BOOK_FAILURE,
  error,
});

export const cancelBorrowBookRequest = id => ({
  type: LENDING.CANCEL_BORROW_BOOK_REQUEST,
  id,
});
export const cancelBorrowBookSuccess = data => ({
  type: LENDING.CANCEL_BORROW_BOOK_SUCCESS,
  data,
});
export const cancelBorrowBookFailure = error => ({
  type: LENDING.CANCEL_BORROW_BOOK_FAILURE,
  error,
});

export const returnBookRequest = id => ({
  type: LENDING.RETURN_BOOK_REQUEST,
  id,
});
export const returnBookSuccess = data => ({
  type: LENDING.RETURN_BOOK_SUCCESS,
  data,
});
export const returnBookFailure = error => ({
  type: LENDING.RETURN_BOOK_FAILURE,
  error,
});

export const loadLatestLendingRequest = bookId => ({
  type: LENDING.LOAD_LATEST_LENDING_REQUEST,
  bookId,
});
export const loadLatestLendingSuccess = data => ({
  type: LENDING.LOAD_LATEST_LENDING_SUCCESS,
  data,
});
export const loadLatestLendingFailure = error => ({
  type: LENDING.LOAD_LATEST_LENDING_FAILURE,
  error,
});

export const loadLendingListRequest = (status, page = 1, size = 20) => ({
  type: LENDING.LOAD_LENDING_LIST_REQUEST,
  status,
  page,
  size,
});
export const loadLendingListSuccess = data => ({
  type: LENDING.LOAD_LENDING_LIST_SUCCESS,
  data,
});
export const loadLendingListFailure = error => ({
  type: LENDING.LOAD_LENDING_LIST_FAILURE,
  error,
});
