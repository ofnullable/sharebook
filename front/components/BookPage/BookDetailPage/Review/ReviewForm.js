import React, { useState } from 'react';

import { useInput, isBlank } from '@utils/inputUtils';
import StarRating from './StarRating';

import { ReviewFormWrapper } from './ReviewForm.styled';
import { InputGroup, Button } from '@styles/common';

const ReviewForm = () => {
  const [contents, handleContentsChange] = useInput();
  const [score, setScore] = useState(0);

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
    </ReviewFormWrapper>
  );
};

export default ReviewForm;
