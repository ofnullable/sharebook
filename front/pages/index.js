import React, { useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';

import BookCard from '@components/BookCard';
import SearchBar from '@components/SearchBar';
import { loadBookListRequest, loadBookListByCategoryRequest } from '@redux/actions/bookActions';
import { loadCategoryListRequest } from '@redux/actions/categoryActions';

import { CenterDiv, SpinIcon, ModalOverlay, LoadingIconWrapper } from '@styles/global';
import { CardWrapper } from '@styles/pages/index';

const Home = () => {
  const { isLoading, data, page, isLast } = useSelector(state => state.book.list);
  const dispatch = useDispatch();

  const handleScroll = () => {
    const scrollTop = Math.max(document.body.scrollTop, document.documentElement.scrollTop);
    const clientHeight = document.documentElement.clientHeight;
    const scrollHeight =
      Math.max(document.body.scrollHeight, document.documentElement.scrollHeight) - clientHeight;

    if (!isLast && scrollHeight === scrollTop && scrollTop) {
      dispatch(loadBookListRequest('', page + 1));
    }
  };

  useEffect(() => {
    window.addEventListener('scroll', handleScroll);
    return () => {
      window.removeEventListener('scroll', handleScroll);
    };
  }, [page, isLast]);

  return (
    <>
      <h1 className='title'>Welcome to Public Share!</h1>

      <SearchBar />

      {isLoading && (
        <>
          <ModalOverlay />
          <LoadingIconWrapper>
            <SpinIcon _size='100px' _color='primary' className='material-icons'>
              autorenew
            </SpinIcon>
          </LoadingIconWrapper>
        </>
      )}

      <CardWrapper>
        {data.length ? (
          data.map(d => <BookCard key={d.id} data={d} />)
        ) : (
          <CenterDiv>
            <p>도서가 존재하지 않습니다.</p>
          </CenterDiv>
        )}
      </CardWrapper>
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
