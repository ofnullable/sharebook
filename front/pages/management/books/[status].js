import React from 'react';

import {
  loadMyBookListRequest,
  loadMyBookListByLendingStatusRequest,
} from '@redux/actions/bookActions';
import BooksPage from '@components/Management/BooksPage';

const Books = ({ status }) => {
  return <BooksPage status={status} />;
};

Books.getInitialProps = async ({ query, store }) => {
  const status = query.status;

  if (status === 'all') {
    store.dispatch(loadMyBookListRequest());
  } else {
    store.dispatch(loadMyBookListByLendingStatusRequest(status.toUpperCase()));
  }

  return { status };
};

export default Books;
