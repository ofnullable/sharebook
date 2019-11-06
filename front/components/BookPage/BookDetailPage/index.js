import React from 'react';
import { useSelector } from 'react-redux';

import LendingButton from './LendingButton';
import Review from './Review';

import { BookDetailHeader, BookImageWrapper, BookDetailWrapper } from './index.styled';
import { CenterDiv } from '@styles/common';

const BookPage = () => {
  const { data, error } = useSelector(state => state.book.detail);

  if (error.status === 404) {
    return (
      <CenterDiv>
        <p>존재하지 않는 도서입니다.</p>
      </CenterDiv>
    );
  }

  return (
    <>
      <BookDetailHeader>
        <BookImageWrapper>
          <img src={data.imageUrl} alt={data.title} />
        </BookImageWrapper>
        <BookDetailWrapper>
          <p>{data.category}</p>
          <h1>{data.title}</h1>
          <p>{`${data.author} | ${data.publisher}`}</p>
          <span>{`${data.owner} | ${data.createdAt}`}</span>
          <div>
            <h3>책 소개</h3>
            <p>{data.description}</p>
          </div>
          <LendingButton detail={data} />
        </BookDetailWrapper>
        <Review />
      </BookDetailHeader>
    </>
  );
};

export default BookPage;
