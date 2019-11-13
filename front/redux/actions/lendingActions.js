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

export const cancelBorrowRequest = (id, target) => ({
  type: LENDING.CANCEL_BORROW_REQUEST,
  id,
  target,
});
export const cancelBorrowSuccess = data => ({
  type: LENDING.CANCEL_BORROW_SUCCESS,
  data,
});
export const cancelBorrowFailure = error => ({
  type: LENDING.CANCEL_BORROW_FAILURE,
  error,
});

export const acceptLendingRequest = (id, target) => ({
  type: LENDING.ACCEPT_LENDING_REQUEST,
  id,
  target,
});
export const acceptLendingSuccess = data => ({
  type: LENDING.ACCEPT_LENDING_SUCCESS,
  data,
});
export const acceptLendingFailure = error => ({
  type: LENDING.ACCEPT_LENDING_FAILURE,
  error,
});

export const rejectLendingRequest = (id, target) => ({
  type: LENDING.REJECT_LENDING_REQUEST,
  id,
  target,
});
export const rejectLendingSuccess = data => ({
  type: LENDING.REJECT_LENDING_SUCCESS,
  data,
});
export const rejectLendingFailure = error => ({
  type: LENDING.REJECT_LENDING_FAILURE,
  error,
});

export const returnBookRequest = (id, target) => ({
  type: LENDING.RETURN_BOOK_REQUEST,
  id,
  target,
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

export const loadMyRequestListByStatusRequest = (status, page = 1, size = 20) => ({
  type: LENDING.LOAD_MY_REQUEST_LIST_BY_STATUS_REQUEST,
  status,
  page,
  size,
});
export const loadMyRequestListByStatusSuccess = data => ({
  type: LENDING.LOAD_MY_REQUEST_LIST_BY_STATUS_SUCCESS,
  data,
});
export const loadMyRequestListByStatusFailure = error => ({
  type: LENDING.LOAD_MY_REQUEST_LIST_BY_STATUS_FAILURE,
  error,
});

export const loadRequestListForMyBookRequest = (status, page = 1, size = 20) => ({
  type: LENDING.LOAD_REQUEST_LIST_FOR_MY_BOOK_REQUEST,
  status,
  page,
  size,
});
export const loadRequestListForMyBookSuccess = data => ({
  type: LENDING.LOAD_REQUEST_LIST_FOR_MY_BOOK_SUCCESS,
  data,
});
export const loadRequestListForMyBookFailure = error => ({
  type: LENDING.LOAD_REQUEST_LIST_FOR_MY_BOOK_FAILURE,
  error,
});
