import axios from 'axios';

export const uploadImageApi = file => {
  return axios.post('/file/image', file, { withCredentials: true });
};

export const registerBookApi = data => {
  return axios.post('/book', data, { withCredentials: true });
};
