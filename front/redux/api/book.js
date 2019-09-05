import axios from 'axios';

export const loadBookListApi = ({ page, size, searchText }) => {
  return axios.get(
    `/books?page=${page}&size=${size}${searchText ? `&searchText=${searchText}` : ''}`,
    { withCredentials: true }
  );
};

export const loadBookApi = id => {
  return axios.get(`/book/${id}`, { withCredentials: true });
};
