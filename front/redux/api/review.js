import axios from 'axios';

export const loadReviewListApi = bookId => {
  return axios.get(`/reviews/book/${bookId}`);
};

export const saveReviewApi = data => {
  return axios.post(`/review`, data, { withCredentials: true });
};
