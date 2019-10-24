import axios from 'axios';

import { LENDING_STATUS } from '@utils/consts';

export const borrowBookApi = bookId => {
  return axios.post(`/book/${bookId}/lending`, {}, { withCredentials: true });
};

export const returnBookApi = bookId => {
  return axios.put(
    `/book/${bookId}/lending`,
    { status: LENDING_STATUS.RETURNED },
    { withCredentials: true }
  );
};

export const loadLendingListApi = ({ status, page, size }) => {
  return axios.get(`/account/lendings/${status}?page=${page}&size=${size}`, {
    withCredentials: true,
  });
};
