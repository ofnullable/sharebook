import React from 'react';
import moment from 'moment';
import { useDispatch, useSelector } from 'react-redux';

import StarRating from './StarRating';

import {
  ReviewItemWrapper,
  ReviewInfo,
  ReviewContents,
  RemoveButton,
  ModifyButton,
} from './ReviewItem.styled';

const ReviewItem = ({ review }) => {
  const user = useSelector(state => state.user.user.data);
  const dispatch = useDispatch();

  const renderButtonDiv = () => {
    if (Object.keys(user).length) {
      if (review.reviewerId === user.id) {
        return (
          <div style={{ textAlign: 'right' }}>
            <RemoveButton>
              삭제<i className='material-icons'>delete_forever</i>
            </RemoveButton>
            <ModifyButton>
              수정<i className='material-icons'>create</i>
            </ModifyButton>
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
        <p className='reviewer'>{review.createdBy}</p>
        <span>{moment(review.createdAt).format('YYYY-MM-DD hh:mm')}</span>
      </ReviewInfo>
      <ReviewContents>
        <p>{review.contents}</p>
        {renderButtonDiv()}
      </ReviewContents>
    </ReviewItemWrapper>
  );
};

export default ReviewItem;
