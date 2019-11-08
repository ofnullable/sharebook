import React from 'react';
import moment from 'moment';
import { useDispatch, useSelector } from 'react-redux';

import StarRating from './StarRating';

import { ReviewItemWrapper, ReviewInfo, ReviewContents } from './ReviewItem.styled';
import { Button } from '@styles/common';

const ReviewItem = ({ review }) => {
  const user = useSelector(state => state.user.user.data);
  const dispatch = useDispatch();

  const renderButtonDiv = () => {
    if (Object.keys(user).length) {
      if (review.reviewerId === user.id) {
        return (
          <div style={{ textAlign: 'right' }}>
            <Button>수정</Button>
            <Button _color='red'>삭제</Button>
          </div>
        );
      }
      // TODO: reply or report button
    }
  };

  return (
    <ReviewItemWrapper>
      <ReviewInfo>
        <StarRating readOnly={true} score={review.score} />
        <p>{review.createdBy}</p>
        <span>{moment(review.createdAt).format('YYYY-MM-DD hh:mm')}</span>
      </ReviewInfo>
      <ReviewContents>
        {review.contents}
        {renderButtonDiv()}
      </ReviewContents>
    </ReviewItemWrapper>
  );
};

export default ReviewItem;
