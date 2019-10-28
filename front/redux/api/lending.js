import axios from 'axios';

import { LENDING_STATUS } from '@utils/consts';

export const borrowBookApi = bookId => {
  return axios.post(`/lending/book/${bookId}`, {}, { withCredentials: true });
};

export const returnBookApi = id => {
  return axios.put(
    `/lending/${id}/${LENDING_STATUS.RETURNED}`,
    {},
    { withCredentials: true }
  );
};

export const loadLatestLendingApi = bookId => {
  return axios.get(`/lending/book/${bookId}/latest`, {
    withCredentials: true,
  });
};

export const loadLendingListApi = ({ status, page, size }) => {
  return axios.get(`/lending/${status}?page=${page}&size=${size}`, {
    withCredentials: true,
  });
};
