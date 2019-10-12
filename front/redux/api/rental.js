import axios from 'axios';

export const rentalBookApi = bookId => {
  return axios.post(`/book/${bookId}/rental`, {}, { withCredentials: true });
};

export const loadRentalListApi = ({ status, page, size }) => {
  return axios.get(`/account/rentals/${status}?page=${page}&size=${size}`, {
    withCredentials: true,
  });
};
