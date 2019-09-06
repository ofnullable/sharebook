import React from 'react';

import {
  BookDetailHeader,
  BookImageWrapper,
  BookDetailWrapper,
  BookInfoWrapper,
} from './index.styled';
import { Button, SpinIcon } from '@styles/global';

function BookDetail({ detail }) {
  const handleButtonClick = e => {
    console.log(e);
  };

  return (
    <BookDetailHeader>
      <BookImageWrapper>
        <img src={`https://placeimg.com/200/300/animals`} alt='' />
      </BookImageWrapper>
      <BookDetailWrapper>
        <BookInfoWrapper>
          <h1>{detail.title}</h1>
          <p>{`${detail.author} | ${detail.publisher} | ${detail.createdAt}`}</p>
          <div>{detail.description}</div>
        </BookInfoWrapper>
        {detail.isLoading ? (
          <Button _color='primary' disabled>
            <SpinIcon _size='16px' className='material-icons'>
              autorenew
            </SpinIcon>
          </Button>
        ) : (
          <Button _color='primary' onClick={handleButtonClick}>
            대여신청
          </Button>
        )}
      </BookDetailWrapper>
    </BookDetailHeader>
  );
}

export default BookDetail;
