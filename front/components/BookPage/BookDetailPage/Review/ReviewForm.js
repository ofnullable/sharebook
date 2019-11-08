import React, { useState, useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';

import StarRating from './StarRating';
import { saveReviewRequest } from '@redux/actions/reviewActions';
import { isBlank } from '@utils/inputUtils';

import { ReviewFormWrapper } from './ReviewForm.styled';
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
  const handleStarClick = e => {
    setScore(e.target.id);
  };

  const handleSubmit = e => {
    e.preventDefault();
    if (!score) {
      return alert('점수를 선택해주세요!');
    }
    if (isBlank(contents)) {
      return alert('리뷰를 작성해주세요!');
    }
    dispatch(saveReviewRequest({ score, contents, bookId: book.id }));
    setContents('');
    setScore(0);
  };

  return (
    <ReviewFormWrapper>
      <p>리뷰를 작성해주세요!</p>
      <form onSubmit={handleSubmit}>
        <StarRating readOnly={false} score={score} clickHandler={handleStarClick} />
        <InputGroup>
          <label htmlFor='review'></label>
          <textarea id='review' value={contents} onChange={handleContentsChange} />
        </InputGroup>
        <Button type='submit' _color='primary'>
          리뷰등록
        </Button>
      </form>
      {error && <p style={{ color: 'red', margin: '1em 0 0' }}>리뷰 등록에 실패했습니다.</p>}
    </ReviewFormWrapper>
  );
};

export default ReviewForm;
