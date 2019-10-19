import axios from 'axios';

import { RENTAL_STATUS } from '@utils/consts';

export const rentalBookApi = bookId => {
  return axios.post(`/book/${bookId}/rental`, {}, { withCredentials: true });
};

export const returnBookApi = bookId => {
  return axios.put(
    `/book/${bookId}/rental`,
    { status: RENTAL_STATUS.RETURNED },
    { withCredentials: true }
  );
};

export const loadRentalListApi = ({ status, page, size }) => {
  return axios.get(`/account/rentals/${status}?page=${page}&size=${size}`, {
    withCredentials: true,
  });
};
