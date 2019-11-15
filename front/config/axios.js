import axios from 'axios';

import { SERVER_URL } from '@utils/consts';

const instance = axios.create({
  baseURL: SERVER_URL,
  withCredentials: true,
});

instance.interceptors.response.use(
  res => res.data,
  err => {
    /**
     * err.response 가 없으면 server와 통신 실패
     */
    console.error(err);
    const error = err.response
      ? err.response.data
      : { status: 503, path: err.config.url, message: 'Service Unavailable' };
    return Promise.reject(error);
  }
);

export default instance;
