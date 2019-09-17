import axios from 'axios';

export const loadRentalInfoApi = () => {
  return axios.get(`/rent`, { withCredentials: true });
};

export const rentalBookApi = bookId => {
  return axios.post(`/rent/${bookId}`, {}, { withCredentials: true });
};
