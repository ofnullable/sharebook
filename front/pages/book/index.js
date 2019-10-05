import React from 'react';
import { useSelector } from 'react-redux';

import { loadBookRequest } from '@redux/actions/bookActions';
import BookDetailPage from '@components/BookPage/BookDetailPage';

const Book = () => {
  const { data, error } = useSelector(state => state.book.detail);

  return <BookDetailPage detail={data} error={error} />;
};

Book.getInitialProps = async ({ query, store }) => {
  const bookId = query.id;

  store.dispatch(loadBookRequest(bookId));
};

export default Book;
