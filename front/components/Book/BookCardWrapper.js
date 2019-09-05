import React from 'react';
import { useSelector } from 'react-redux';

import Card from './BookCard';

import { Wrapper } from './BookCardWrapper.styled';
import { SpinIcon, CenterAlignDiv } from '@styles/global';

function BookCardWrapper() {
  const { isLoading, data } = useSelector(state => state.book.list);

  return (
    <div>
      {isLoading ? (
        <Wrapper>
          <SpinIcon _size='48px' _color='primary' className='material-icons'>
            autorenew
          </SpinIcon>
        </Wrapper>
      ) : data.length ? (
        data.map(d => <Card key={d.id} data={d} />)
      ) : (
        <CenterAlignDiv>도서가 존재하지 않습니다.</CenterAlignDiv>
      )}
    </div>
  );
}

export default BookCardWrapper;
