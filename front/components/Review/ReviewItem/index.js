import React, { useState, useCallback } from 'react';
import moment from 'moment';
import { useDispatch, useSelector } from 'react-redux';

import StarRating from './StarRating';
import LoadingOverlay from '@components/common/LoadingOverlay';
import { updateReviewRequest, deleteReviewRequest } from '@redux/actions/reviewActions';

import {
  ReviewItemWrapper,
  ReviewInfo,
  ReviewContents,
  DangerButton,
  WarningButton,
} from './index.styled';
import { InputGroup, Button } from '@styles/common';

const ReviewItem = ({ review }) => {
  const [editable, setEditable] = useState(false);
  const [score, setScore] = useState(review.score);
  const [contents, setContents] = useState(review.contents);
  const user = useSelector(state => state.user.user.data);
  const { isLoading } = useSelector(state => state.review.updateRequest);
  const dispatch = useDispatch();

  const handleDelete = () => {
    if (confirm('리뷰를 삭제하시겠습니까?')) {
      dispatch(deleteReviewRequest(review.id));
    }
  };

  const handleUpdate = () => {
    setEditable(false);
    if (score === review.score && contents === review.contents) return;
    dispatch(updateReviewRequest({ id: review.id, score, contents }));
  };

  const toggleEditable = () => {
    if (editable) {
      setScore(review.score);
      setContents(review.contents);
    }
    setEditable(!editable);
  };

  const handleEditContents = e => {
    setContents(e.target.value);
  };

  const handleStarClick = useCallback(e => {
    setScore(e.target.id);
  }, []);

  const renderButtonDiv = () => {
    if (editable) {
      return (
        <div>
          <Button _color='red' onClick={toggleEditable}>
            취소
          </Button>
          <Button onClick={handleUpdate}>수정</Button>
        </div>
      );
    }

    if (Object.keys(user).length) {
      if (review.reviewerId === user.id) {
        return (
          <div>
            <DangerButton onClick={handleDelete}>
              <i className='material-icons-outlined'>delete_forever</i>삭제
            </DangerButton>
            <WarningButton onClick={toggleEditable}>
              <i className='material-icons-outlined'>create</i>수정
            </WarningButton>
          </div>
        );
      }
    }
  };

  return (
    <ReviewItemWrapper>
      {isLoading && <LoadingOverlay />}
      <ReviewInfo>
        <StarRating
          readOnly={!editable}
          score={editable ? score : review.score}
          clickHandler={editable && handleStarClick}
        />
        <p className='reviewer'>{review.modifiedBy}</p>
        <span>{moment(review.modifiedAt).format('YYYY.MM.DD hh:mm')}</span>
      </ReviewInfo>
      <ReviewContents>
        {editable ? (
          <InputGroup>
            <label htmlFor='review' />
            <textarea id='review' value={contents} onChange={handleEditContents} />
          </InputGroup>
        ) : (
          <p>{review.contents}</p>
        )}
        {renderButtonDiv()}
      </ReviewContents>
    </ReviewItemWrapper>
  );
};

export default ReviewItem;
