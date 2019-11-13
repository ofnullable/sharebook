import React from 'react';

import { loadMyBookListRequest } from '@redux/actions/bookActions';
import { loadRequestListForMyBookRequest } from '@redux/actions/lendingActions';
import BooksPage from '@components/Management/BooksPage';

const Books = ({ status }) => {
  return <BooksPage status={status} />;
};

Books.getInitialProps = async ({ query, store }) => {
  const status = query.status;

  if (status === 'all') {
    store.dispatch(loadMyBookListRequest());
  } else {
    store.dispatch(loadRequestListForMyBookRequest(status.toUpperCase()));
  }

  return { status };
};

export default Books;
