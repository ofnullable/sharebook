import React from 'react';

import { loadBookRequest } from '@redux/actions/bookActions';
import { loadLatestLendingRequest } from '@redux/actions/lendingActions';
import { loadReviewListRequest } from '@redux/actions/reviewActions';
import BookDetailPage from '@components/BookPage/BookDetailPage';

const Book = () => {
  return <BookDetailPage />;
};

Book.getInitialProps = async ({ query, store }) => {
  const bookId = query.id;

  store.dispatch(loadBookRequest(bookId));
  store.dispatch(loadLatestLendingRequest(bookId));
  store.dispatch(loadReviewListRequest(bookId));
};

export default Book;
