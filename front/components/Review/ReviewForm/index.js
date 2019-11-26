import React, { useState, useCallback } from 'react';
import { useDispatch, useSelector } from 'react-redux';

import StarRating from '../ReviewItem/StarRating';
import { saveReviewRequest } from '@redux/actions/reviewActions';
import { isBlank } from '@utils';

import { ReviewFormWrapper } from './index.styled';
import { InputGroup, Button } from '@styles/common';

const ReviewForm = () => {
  const [contents, setContents] = useState('');
  const [score, setScore] = useState(0);

  const book = useSelector(state => state.book.detail.data);
  const { error } = useSelector(state => state.review.saveRequest);
  const dispatch = useDispatch();

  const handleContentsChange = e => {
    setContents(e.target.value);
  };

  const handleStarClick = useCallback(e => {
    setScore(e.target.id);
  }, []);

  const handleSubmit = e => {
    e.preventDefault();
    if (!score) {
      return alert('점수를 선택해주세요!');
    }
    if (isBlank(contents)) {
      return alert('리뷰를 작성해주세요!');
    }
    setScore(0);
    setContents('');
    dispatch(saveReviewRequest({ score, contents, bookId: book.id }));
  };

  return (
    <ReviewFormWrapper>
      {error ? <p style={{ color: 'red' }}>{error.message}</p> : <p>리뷰를 작성해주세요!</p>}
      <form onSubmit={handleSubmit}>
        <StarRating readOnly={false} score={score} clickHandler={handleStarClick} />
        <InputGroup>
          <label htmlFor='review' />
          <textarea id='review' value={contents} onChange={handleContentsChange} />
        </InputGroup>
        <Button type='submit' _color='primary'>
          <i className='material-icons-outlined'>rate_review</i>리뷰등록
        </Button>
      </form>
    </ReviewFormWrapper>
  );
};

export default ReviewForm;
