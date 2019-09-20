import React from 'react';
import { useSelector } from 'react-redux';

import { loadBookRequest } from '@redux/actions/bookActions';
import { loadRentalInfoRequest } from '@redux/actions/rentalActions';
import BookDetail from '@components/BookDetail';

const Book = () => {
  const { data } = useSelector(state => state.book.detail);

  return (
    <>
      <BookDetail detail={data} />
    </>
  );
};

Book.getInitialProps = async ({ query, store }) => {
  const bookId = query.id;

  store.dispatch(loadBookRequest(bookId));
  store.dispatch(loadRentalInfoRequest(bookId));
};

export default Book;
