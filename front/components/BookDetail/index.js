import React from 'react';
import { useRouter } from 'next/router';
import { useSelector, useDispatch } from 'react-redux';

import { rentalBookRequest } from '@redux/actions/rentalActions';

import {
  BookDetailHeader,
  BookImageWrapper,
  BookDetailWrapper,
  BookInfoWrapper,
} from './index.styled';
import { Button, SpinIcon } from '@styles/global';

function BookDetail({ detail }) {
  const { user } = useSelector(state => state.user);
  const dispatch = useDispatch();
  const router = useRouter();

  const handleRent = () => {
    dispatch(rentalBookRequest(detail.id));
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

    if (user.data.id) {
      return (
        <Button _color='primary' onClick={handleRent}>
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
}

export default BookDetail;
