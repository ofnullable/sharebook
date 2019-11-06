import React from 'react';
import { useSelector } from 'react-redux';

import ReviewForm from './ReviewForm';
import ReviewItem from './ReviewItem';

import { ReviewTitle, EmptyReview } from './index.styled';

const Review = () => {
  const user = useSelector(state => state.user.user);
  const book = useSelector(state => state.book.detail);
  const { data, isLoading } = useSelector(state => state.review.reviewList);

  const canWriteReview = () => {
    /**
     * Conditions of can not write review
     * 1. Is not sign in.
     * 2. Current user is owner.
     * 3. Already reviewed.
     */
    if (!user.isSignedIn) return false;
    if (book.data.owner === user.data.name) return false;
    return true;
  };

  return (
    <>
      <ReviewTitle>리뷰</ReviewTitle>
      {canWriteReview() && <ReviewForm user={user} />}
      {data.length ? (
        data.map(review => <ReviewItem key={review.id} review={review} />)
      ) : (
        <EmptyReview>리뷰가 존재하지 않아요 ㅠ^ㅠ</EmptyReview>
      )}
    </>
  );
};

export default Review;
