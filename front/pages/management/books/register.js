import React, { useEffect } from 'react';
import { useRouter } from 'next/router';
import { useSelector } from 'react-redux';

import BookRegisterPage from '@components/BookPage/BookRegisterPage';
import { loadCategoryListRequest } from '@redux/actions/categoryActions';

const BookRegister = () => {
  const { isSignedIn, isLoading } = useSelector(state => state.user.user);
  const router = useRouter();

  useEffect(() => {
    if (!isLoading && !isSignedIn) {
      router.push('/signin');
    }
  }, [isLoading, isSignedIn]);

  return <BookRegisterPage />;
};

BookRegister.getInitialProps = async ({ store }) => {
  const categoryList = store.getState().category.list.data;
  if (!categoryList.length) {
    store.dispatch(loadCategoryListRequest());
  }
  return { categoryList };
};

export default BookRegister;
