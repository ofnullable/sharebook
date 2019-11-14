import axios from '@axios';

export const borrowBookApi = bookId => {
  return axios.post(`/lending/book/${bookId}`, {});
};

export const changeLendingStatusApi = ({ id, status }) => {
  return axios.put(`/lending/${id}/${status}`, {});
};

export const loadLatestLendingApi = bookId => {
  return axios.get(`/lending/book/${bookId}/latest`);
};

export const loadMyRequestListByStatusApi = ({ status, page, size }) => {
  return axios.get(`/lendings/${status}?page=${page}&size=${size}`);
};

export const loadRequestListForMyBookApi = ({ status, page, size }) => {
  return axios.get(`/mybook/lendings/${status}?page=${page}&size=${size}`);
};
