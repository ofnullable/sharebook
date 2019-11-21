import React from 'react';
import { useSelector } from 'react-redux';

import ReviewItem from '@components/Review/ReviewItem';
import ReviewForm from '@components/Review/ReviewForm';

import { ReviewWrapper, ReviewTitle } from './ReviewList.styled.js';
import { EmptyReview } from '@components/Review/ReviewItem/index.styled';

const ReviewList = () => {
  const user = useSelector(state => state.user.user);
  const book = useSelector(state => state.book.detail);
  const { data, isLoading } = useSelector(state => state.review.reviewList);

  const canReview = () => {
    if (!user.isSignedIn) return false;
    return book.data.owner !== user.data.name;
  };

  return (
    <ReviewWrapper>
      <ReviewTitle>리뷰</ReviewTitle>
      {canReview() && <ReviewForm user={user} />}
      {data.length ? (
        data.map(review => <ReviewItem key={review.id} review={review} />)
      ) : (
        <EmptyReview>리뷰가 존재하지 않아요 ㅠ^ㅠ</EmptyReview>
      )}
    </ReviewWrapper>
  );
};

export default ReviewList;
