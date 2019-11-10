import React from 'react';
import { useSelector } from 'react-redux';

import ReviewForm from './ReviewForm';
import ReviewItem from './ReviewItem';

import { ReviewWrapper, ReviewTitle, EmptyReview } from './index.styled';

const Review = () => {
  const user = useSelector(state => state.user.user);
  const book = useSelector(state => state.book.detail);
  const { data, isLoading } = useSelector(state => state.review.reviewList);

  const isNotOwner = () => {
    if (!user.isSignedIn) return true;
    return book.data.owner !== user.data.name;
  };

  return (
    <ReviewWrapper>
      <ReviewTitle>리뷰</ReviewTitle>
      {isNotOwner() && <ReviewForm user={user} />}
      {data.length ? (
        data.map(review => <ReviewItem key={review.id} review={review} />)
      ) : (
        <EmptyReview>리뷰가 존재하지 않아요 ㅠ^ㅠ</EmptyReview>
      )}
    </ReviewWrapper>
  );
};

export default Review;
