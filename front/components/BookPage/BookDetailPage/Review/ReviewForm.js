import React from 'react';

import { useInput } from '@utils/inputUtils';
import StarRating from './StarRating';

import { ReviewFormWrapper } from './ReviewForm.styled';
import { InputGroup, Button } from '@styles/common';

const ReviewForm = () => {
  const [contents, handleContentsChange] = useInput();

  const handleSubmit = e => {
    e.preventDefault();
  };

  return (
    <ReviewFormWrapper>
      <p>리뷰를 작성해주세요!</p>
      <form onSubmit={handleSubmit}>
        {/* star rating component (editable) */}
        <StarRating readOnly={false} score={0} />
        <InputGroup>
          <label htmlFor='review'></label>
          <textarea
            id='review'
            style={{ width: '90%', margin: '0 auto' }}
            rows='5'
            value={contents}
            onChange={handleContentsChange}
          />
        </InputGroup>
        <Button type='submit' _color='primary'>
          리뷰등록
        </Button>
      </form>
    </ReviewFormWrapper>
  );
};

export default ReviewForm;
