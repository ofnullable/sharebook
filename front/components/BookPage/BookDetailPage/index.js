import React from 'react';
import moment from 'moment';
import { useSelector } from 'react-redux';

import DetailButton from './DetailButton';
import ReviewList from './ReviewList';
import { getAvatar } from '@utils';
import { IMAGE_BASE_URL } from '@utils/consts';

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
          <img src={`${IMAGE_BASE_URL}${data.imageUri}`} alt={data.title} />
        </BookImageWrapper>
        <BookDetailWrapper>
          <p>{data.category}</p>
          <h1>{data.title}</h1>
          <p>{`${data.author} | ${data.publisher}`}</p>
          <span>
            <img src={getAvatar(data.ownerAvatar, data.createdBy, 25)} alt='owner avatar' />
          </span>
          <span>{`${data.owner} | ${moment(data.createdAt).format('YYYY.MM.DD')}`}</span>
          <div>
            <h3>책 소개</h3>
            <p>{data.description}</p>
          </div>
          <DetailButton detail={data} />
        </BookDetailWrapper>
        <ReviewList />
      </BookDetailHeader>
    </>
  );
};

export default BookPage;
