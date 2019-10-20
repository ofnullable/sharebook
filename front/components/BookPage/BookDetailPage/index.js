import React from 'react';

import RentalButton from './RentalButton';

import {
  BookDetailHeader,
  BookImageWrapper,
  BookDetailWrapper,
  BookInfoWrapper,
} from './index.styled';
import { CenterDiv } from '@styles/common';

const BookPage = ({ detail, error }) => {
  if (error.status === 404) {
    return (
      <CenterDiv>
        <p>존재하지 않는 도서입니다.</p>
      </CenterDiv>
    );
  }

  return (
    <BookDetailHeader>
      <BookImageWrapper>
        <img src={detail.imageUrl} alt={detail.title} />
      </BookImageWrapper>
      <BookDetailWrapper>
        <BookInfoWrapper>
          <p>{detail.category}</p>
          <h1>{detail.title}</h1>
          <p>{`${detail.author} | ${detail.publisher}`}</p>
          <span>{`${detail.owner} | ${detail.createdAt}`}</span>
          <p>{detail.description}</p>
        </BookInfoWrapper>
        <RentalButton detail={detail} />
      </BookDetailWrapper>
    </BookDetailHeader>
  );
};

export default BookPage;
