import React from 'react';

import { loadBookRequest } from '@redux/actions/bookActions';
import { loadLatestLendingRequest } from '@redux/actions/lendingActions';
import { loadReviewListRequest } from '@redux/actions/reviewActions';
import BookDetailPage from '@components/BookPage/BookDetailPage';

const Book = () => {
  return <BookDetailPage />;
};

Book.getInitialProps = async ({ query, store }) => {
  const id = query.id;

  store.dispatch(loadBookRequest(id));
  store.dispatch(loadLatestLendingRequest(id));
  store.dispatch(loadReviewListRequest(id));

  return { id };
};

export default Book;
