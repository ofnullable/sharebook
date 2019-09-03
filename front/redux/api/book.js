import axios from 'axios';

export const loadBookListApi = () => {
  return axios.get('/books', { withCredentials: true });
};
