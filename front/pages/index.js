import React from 'react';
import { useSelector } from 'react-redux';

import BookCard from '@components/BookCard';
import SearchBar from '@components/SearchBar';
import { loadBookListRequest } from '@redux/actions/bookActions';
import { CenterDiv, SpinIcon } from '@styles/global';
import { CardWrapper } from '@styles/pages/index.styled';

const Home = () => {
  const { isLoading, data } = useSelector(state => state.book.list);

  const renderPosts = () => {
    if (isLoading) {
      return (
        <CenterDiv>
          <SpinIcon _size='48px' _color='primary' className='material-icons'>
            autorenew
          </SpinIcon>
        </CenterDiv>
      );
    }
    if (data.length) {
      return data.map(d => <BookCard key={d.id} data={d} />);
    }
    return (
      <CenterDiv>
        <p>도서가 존재하지 않습니다.</p>
      </CenterDiv>
    );
  };

  return (
    <>
      <h1 className='title'>Welcome to Public Share!</h1>

      <SearchBar />

      <CardWrapper>{renderPosts()}</CardWrapper>
    </>
  );
};

Home.getInitialProps = async ctx => {
  ctx.store.dispatch(loadBookListRequest());
};

export default Home;
