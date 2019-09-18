import { RENTAL } from '@redux/actionTypes';

export const loadRentalInfoRequest = () => ({
  type: RENTAL.LOAD_MY_RENTAL_INFO_REQUEST,
});
export const loadRentalInfoSuccess = data => ({
  type: RENTAL.LOAD_MY_RENTAL_INFO_SUCCESS,
  data,
});
export const loadRentalInfoFailure = error => ({
  type: RENTAL.LOAD_MY_RENTAL_INFO_FAILURE,
  error,
});

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
