import axios from 'axios';

export const loadReviewListApi = bookId => {
  return axios.get(`/reviews/book/${bookId}`, { withCredentials: true });
};

export const saveReviewApi = data => {
  return axios.post(`/review`, data, { withCredentials: true });
};

export const updateReviewApi = data => {
  return axios.put(`/review/${data.id}`, data, { withCredentials: true });
};

export const deleteReviewApi = id => {
  return axios.delete(`/review/${id}`, { withCredentials: true });
};
