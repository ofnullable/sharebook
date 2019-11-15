import axios from '@axios';

export const emailDuplicationCheckApi = email => {
  return axios.get(`/account/duplicate?email=${email}`);
};

export const signUpApi = user => {
  return axios.post('/account', user);
};

export const signInApi = user => {
  return axios.post('/auth/sign-in', user);
};

export const signOutApi = () => {
  return axios.post('/auth/sign-out', {});
};

export const loadUserApi = id => {
  return axios.get(`/account/${id}`);
};
