import { RENTAL } from '@redux/actionTypes';

export const rentalBookRequest = id => ({
  type: RENTAL.RENTAL_BOOK_REQUEST,
  id,
});
export const rentalBookSuccess = data => ({
  type: RENTAL.RENTAL_BOOK_SUCCESS,
  data,
});
export const rentalBookFailure = error => ({
  type: RENTAL.RENTAL_BOOK_FAILURE,
  error,
});

export const loadRentalListRequest = (status, page = 1, size = 20) => ({
  type: RENTAL.LOAD_RENTAL_LIST_REQUEST,
  status,
  page,
  size,
});
export const loadRentalListSuccess = data => ({
  type: RENTAL.LOAD_RENTAL_LIST_SUCCESS,
  data,
});
export const loadRentalListFailure = error => ({
  type: RENTAL.LOAD_RENTAL_LIST_FAILURE,
  error,
});
