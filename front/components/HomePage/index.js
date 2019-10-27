import React, { useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';

import SearchBar from './SearchBar';
import CategoryButton from './CategoryButton';
import BookCard from '@components/BookCard';
import { loadBookListRequest, loadBookListByCategoryRequest } from '@redux/actions/bookActions';

import { ConditionWrapper } from './index.styled';
import {
  CardWrapper,
  CenterDiv,
  SpinIcon,
  ScreenOverlay,
  LoadingIconWrapper,
} from '@styles/common';

const HomePage = ({ category }) => {
  const { isLoading, data, page, isLast } = useSelector(state => state.book.list);
  const categoryList = useSelector(state => state.category.list);
  const dispatch = useDispatch();

  const handleScroll = () => {
    const scrollTop = Math.max(document.body.scrollTop, document.documentElement.scrollTop);
    const clientHeight = document.documentElement.clientHeight;
    const scrollHeight =
      Math.max(document.body.scrollHeight, document.documentElement.scrollHeight) - clientHeight;

    if (!isLast && scrollHeight === scrollTop && scrollTop) {
      if (category) {
        dispatch(loadBookListByCategoryRequest(category, page + 1));
        return;
      }
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
      <h1 className='title'>Welcome to sharebook!</h1>

      <ConditionWrapper>
        <SearchBar />
        {!categoryList.isLoading && [
          <CategoryButton key='ALL' name='ALL' />,
          ...categoryList.data.map(c => <CategoryButton key={c.id} name={c.name} />),
        ]}
      </ConditionWrapper>

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
        {data.length ? (
          data.map(book => <BookCard key={book.id} data={book} />)
        ) : (
          <CenterDiv>
            <p>도서가 존재하지 않습니다.</p>
          </CenterDiv>
        )}
      </CardWrapper>
    </>
  );
};

export default HomePage;
