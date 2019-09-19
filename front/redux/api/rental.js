import axios from 'axios';

export const loadRentalInfoApi = () => {
  return axios.get(`/rental`, { withCredentials: true });
};

export const loadRentalInfoByBookIdApi = bookId => {
  return axios.get(`/rental/${bookId}`, { withCredentials: true });
};

export const rentalBookApi = bookId => {
  return axios.post(`/rental/${bookId}`, {}, { withCredentials: true });
};
