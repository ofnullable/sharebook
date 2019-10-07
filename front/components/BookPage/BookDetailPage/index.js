import React from 'react';
import { useRouter } from 'next/router';
import { useSelector } from 'react-redux';

import RentalButton from './RentalButton';

import {
  BookDetailHeader,
  BookImageWrapper,
  BookDetailWrapper,
  BookInfoWrapper,
} from './index.styled';
import { Button, CenterDiv } from '@styles/common';

const BookPage = ({ detail, error }) => {
  const { user } = useSelector(state => state.user);

  const router = useRouter();

  const renderButton = () => {
    if (!user.data.id) {
      return (
        <Button _color='primary' onClick={() => router.push('/signin')}>
          로그인하기
        </Button>
      );
    } else {
      if (user.data.name === detail.owner) {
        // TODO: Add toggle rental history modal button
        return null;
      }
      return <RentalButton detail={detail} wasRent={detail.currentRenterId === user.data.id} />;
    }
  };

  if (error.status === 404) {
    return (
      <CenterDiv>
        <p>도서가 존재하지 않습니다.</p>
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
        {renderButton()}
      </BookDetailWrapper>
    </BookDetailHeader>
  );
};

export default BookPage;
