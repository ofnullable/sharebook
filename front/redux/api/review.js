import axios from 'axios';

export const loadReviewListApi = bookId => {
  return axios.get(`/reviews/book/${bookId}`);
};
