import axios from 'axios';

export const signUpApi = user => {
  return axios.post('/account', user, { withCredentials: true });
};

export const signInApi = user => {
  return axios.post('/auth/sign-in', user, { withCredentials: true });
};

export const signOutApi = () => {
  return axios.post('/auth/sign-out', {}, { withCredentials: true });
};

export const loadUserApi = id => {
  return axios.get(`/account/${id}`, { withCredentials: true });
};
