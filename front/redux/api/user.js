import axios from 'axios';

export const signInApi = payload => {
  return axios.post('/auth/sign-in', payload, { withCredentials: true });
};
