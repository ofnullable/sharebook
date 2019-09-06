import React from 'react';
import { useSelector } from 'react-redux';

import { loadBookRequest } from '@redux/actions/bookActions';
import BookDetail from '@components/BookDetail';

const Book = () => {
  const { data } = useSelector(state => state.book.detail);

  return (
    <>
      <BookDetail detail={data} />
    </>
  );
};

Book.getInitialProps = async ctx => {
  const bookId = ctx.query.id;
  ctx.store.dispatch(loadBookRequest(bookId));
  return { id: bookId };
};

export default Book;
