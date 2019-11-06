import React from 'react';

import StarRating from './StarRating';

import { ReviewItemWrapper, ReviewInfo, ReviewContents } from './ReviewItem.styled';

const ReviewItem = ({ review }) => {
  return (
    <ReviewItemWrapper>
      <ReviewInfo>
        {/* star rating component (readonly) */}
        <StarRating readOnly={true} score={review.score} />
        <p style={{ margin: '0.5em' }}>{review.createdBy}</p>
        <span>{review.createdAt}</span>
      </ReviewInfo>
      <ReviewContents>{review.contents}</ReviewContents>
    </ReviewItemWrapper>
  );
};

export default ReviewItem;
