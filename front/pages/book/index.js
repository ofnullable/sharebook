import React from 'react';
import { useSelector } from 'react-redux';

import { loadBookRequest } from '@redux/actions/bookActions';
import { loadLatestLendingRequest } from "@redux/actions/lendingActions";
import BookDetailPage from '@components/BookPage/BookDetailPage';

const Book = () => {
  const { detail } = useSelector(state => state.book);

  return <BookDetailPage detail={detail.data} error={detail.error} />;
};

Book.getInitialProps = async ({ query, store }) => {
  const bookId = query.id;

  store.dispatch(loadBookRequest(bookId));
  store.dispatch(loadLatestLendingRequest(bookId));
};

export default Book;
