import axios from 'axios';

export const signInApi = payload => {
  console.log(payload);
  return axios.post('/auth/sign-in', payload);
};
