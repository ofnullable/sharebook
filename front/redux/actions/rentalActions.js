import { RENTAL } from '@redux/actionTypes';

export const rentalBookRequest = id => ({
  type: RENTAL.RENTAL_BOOK_REQUEST,
  id,
});
export const rentalBookSuccess = (data, status) => ({
  type: RENTAL.RENTAL_BOOK_SUCCESS,
  data,
  status,
});
export const rentalBookFailure = (error, status) => ({
  type: RENTAL.RENTAL_BOOK_FAILURE,
  error,
  status,
});
