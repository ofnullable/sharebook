import axios from 'axios';

export const loadRentalInfoApi = () => {
  return axios.get(`/rental`, { withCredentials: true });
};

export const loadRentalInfoByBookIdApi = bookId => {
  return axios.get(`book/${bookId}/rental/`, { withCredentials: true });
};

export const rentalBookApi = bookId => {
  return axios.post(`/book/${bookId}/rental`, {}, { withCredentials: true });
};
