import React from 'react';
import { useRouter } from 'next/router';
import { useSelector } from 'react-redux';

import {
  BookDetailHeader,
  BookImageWrapper,
  BookDetailWrapper,
  BookInfoWrapper,
} from './index.styled';
import { Button, SpinIcon } from '@styles/global';

function BookDetail({ detail }) {
  const { user } = useSelector(state => state.user);
  const router = useRouter();

  const handleRequest = e => {
    console.log(e);
  };

  const renderButton = () => {
    if (detail.isLoading) {
      return (
        <Button _color='primary' disabled>
          <SpinIcon _size='16px' className='material-icons'>
            autorenew
          </SpinIcon>
        </Button>
      );
    }

    if (user.id) {
      return (
        <Button _color='primary' onClick={handleRequest}>
          대여신청
        </Button>
      );
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
        <img src={`https://placeimg.com/200/300/animals`} alt='' />
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
}

export default BookDetail;
