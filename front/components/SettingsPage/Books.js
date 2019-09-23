import React from 'react';
import { useSelector } from 'react-redux';

import BookCard from '@components/BookCard';

import { Title } from './index.styled';
import { HeaderWrapper, RegisterButton } from './Books.styled';
import {
  CardWrapper,
  CenterDiv,
  SpinIcon,
  ScreenOverlay,
  LoadingIconWrapper,
} from '@styles/common';

const Books = () => {
  const { isLoading, data } = useSelector(state => state.book.myBooks);
  return (
    <>
      <HeaderWrapper>
        <Title>도서관리</Title>
        <RegisterButton _color='primary'>도서등록</RegisterButton>
      </HeaderWrapper>

      {isLoading && (
        <>
          <ScreenOverlay />
          <LoadingIconWrapper>
            <SpinIcon _size='100px' _color='primary' className='material-icons'>
              autorenew
            </SpinIcon>
          </LoadingIconWrapper>
        </>
      )}

      <CardWrapper>
        {!isLoading &&
          (data.length ? (
            data.map(d => <BookCard key={d.id} data={d} />)
          ) : (
            <CenterDiv>
              <p>도서가 존재하지 않습니다.</p>
            </CenterDiv>
          ))}
      </CardWrapper>
    </>
  );
};

export default Books;
