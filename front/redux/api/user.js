import axios from 'axios';

export const signInApi = payload => {
  return axios.post('/auth/sign-in', payload, { withCredentials: true });
};

export const signOutApi = () => {
  return axios.post('/auth/sign-out', {}, { withCredentials: true });
};

export const loadUserApi = id => {
  return axios.get(`/account/${id}`, { withCredentials: true });
};
