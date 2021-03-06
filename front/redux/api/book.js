import axios from '@axios';

export const loadBookListApi = ({ searchText, page, size }) => {
  return axios.get(
    `/books?page=${page}&size=${size}${
      searchText ? `&searchText=${encodeURIComponent(searchText)}` : ''
    }`
  );
};

export const loadBookListByCategoryApi = ({ category, page, size }) => {
  return axios.get(`/books/category/${encodeURIComponent(category)}?page=${page}&size=${size}`);
};

export const loadBookApi = id => {
  return axios.get(`/book/${id}`);
};

export const loadMyBookListApi = ({ page, size }) => {
  return axios.get(`/account/0/books?page=${page}&size=${size}`);
};
