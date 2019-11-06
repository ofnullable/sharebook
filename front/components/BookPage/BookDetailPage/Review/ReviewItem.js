import React from 'react';
import moment from 'moment';

import StarRating from './StarRating';

import { ReviewItemWrapper, ReviewInfo, ReviewContents } from './ReviewItem.styled';

const ReviewItem = ({ review }) => {
  return (
    <ReviewItemWrapper>
      <ReviewInfo>
        <StarRating readOnly={true} score={review.score} />
        <p>{review.createdBy}</p>
        <span>{moment(review.createdAt).format('YYYY-MM-DD hh:mm')}</span>
      </ReviewInfo>
      <ReviewContents>{review.contents}</ReviewContents>
    </ReviewItemWrapper>
  );
};

export default ReviewItem;
