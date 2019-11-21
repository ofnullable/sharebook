import axios from '@axios';

export const loadReviewListApi = bookId => {
  return axios.get(`/reviews/book/${bookId}`);
};

export const saveReviewApi = data => {
  return axios.post(`/review`, data);
};

export const updateReviewApi = data => {
  return axios.put(`/review/${data.id}`, data);
};

export const deleteReviewApi = id => {
  return axios.delete(`/review/${id}`);
};

export const loadMyReviewListApi = ({ page, size }) => {
  return axios.get(`/account/0/reviews?page=${page}&size=${size}`);
};
