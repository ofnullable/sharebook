import axios from 'axios';

export const loadBookListApi = ({ searchText, page, size }) => {
  return axios.get(
    `/books?page=${page}&size=${size}${
      searchText ? `&searchText=${encodeURIComponent(searchText)}` : ''
    }`,
    { withCredentials: true }
  );
};

export const loadBookListByCategoryApi = ({ category, page, size }) => {
  return axios.get(`/books/category/${encodeURIComponent(category)}?page=${page}&size=${size}`, {
    withCredentials: true,
  });
};

export const loadBookApi = id => {
  return axios.get(`/book/${id}`, { withCredentials: true });
};
