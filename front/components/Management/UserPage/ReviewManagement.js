import React from 'react';
import { useSelector } from 'react-redux';

const ReviewManagement = () => {
  const { myReviewList } = useSelector(state => state.review);
  return <div></div>;
};

export default ReviewManagement;
