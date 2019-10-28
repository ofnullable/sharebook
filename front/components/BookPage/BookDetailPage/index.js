import React from 'react';

import LendingButton from './LendingButton';

import {
  BookDetailHeader,
  BookImageWrapper,
  BookDetailWrapper,
  BookInfoWrapper,
} from './index.styled';
import { CenterDiv } from '@styles/common';

const BookPage = ({ detail, error, latestLending }) => {
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
        <LendingButton detail={detail} lending={latestLending.data} />
      </BookDetailWrapper>
    </BookDetailHeader>
  );
};

export default BookPage;
