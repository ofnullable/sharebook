import React, { useEffect } from 'react';
import { useSelector } from 'react-redux';

import BookCard from '@components/BookCard';
import SearchBar from '@components/SearchBar';
import { loadBookListRequest, loadBookListByCategoryRequest } from '@redux/actions/bookActions';
import { loadCategoryListRequest } from '@redux/actions/categoryActions';

import { CenterDiv, SpinIcon } from '@styles/global';
import { CardWrapper } from '@styles/pages/index.styled';

const Home = () => {
  const { isLoading, data, page } = useSelector(state => state.book.list);

  useEffect(() => {
    console.log(page);
    // window scroll event for infinite scroll
  }, [page]);

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

Home.getInitialProps = async ({ query, store }) => {
  const categoryList = store.getState().category.list;
  if (!categoryList.length) {
    store.dispatch(loadCategoryListRequest());
  }

  const searchText = query.searchText;
  if (searchText) {
    store.dispatch(loadBookListRequest(searchText));
    return;
  }
  const category = query.category;
  if (!category || category === 'ALL') {
    store.dispatch(loadBookListRequest());
  } else {
    store.dispatch(loadBookListByCategoryRequest(category));
  }
};

export default Home;
