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
import { Button } from '@styles/common';

const BookPage = ({ detail }) => {
  const { user } = useSelector(state => state.user);
  const { histories } = useSelector(state => state.rental);

  const router = useRouter();

  const renderButton = () => {
    if (user.data.id) {
      if (user.data.name === detail.owner) {
        // TODO: Add toggle rental history modal button
        return null;
      }
      return <RentalButton detail={detail} histories={histories.data} />;
    } else {
      return (
        <Button _color='primary' onClick={() => router.push('/signin')}>
          로그인하기
        </Button>
      );
    }
  };

  return (
    <BookDetailHeader>
      <BookImageWrapper>
        <img src={detail.imageUrl} alt={detail.title} />
      </BookImageWrapper>
      <BookDetailWrapper>
        <BookInfoWrapper>
          <h1>{detail.title}</h1>
          <p>{`${detail.category} | ${detail.author} | ${detail.publisher}`}</p>
          <span>{`${detail.owner} | ${detail.createdAt}`}</span>
          <div>{detail.description}</div>
        </BookInfoWrapper>
        {renderButton()}
      </BookDetailWrapper>
    </BookDetailHeader>
  );
};

export default BookPage;
