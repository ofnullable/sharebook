import axios from 'axios';

export const loadCategoryListApi = () => {
  return axios.get('/categories', { withCredentials: true });
};
