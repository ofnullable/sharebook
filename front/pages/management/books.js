import React from 'react';

import {
  loadMyBookListRequest,
  loadMyBookListByRentalStatusRequest,
} from '@redux/actions/bookActions';
import BooksPage from '@components/Management/BooksPage';
import { RENTAL_STATUS } from '@utils/consts';

const Books = ({ status }) => {
  return <BooksPage status={status} />;
};

Books.getInitialProps = async ({ query, store }) => {
  const status = query.status;

  if (!status) {
    store.dispatch(loadMyBookListRequest());
  } else {
    store.dispatch(loadMyBookListByRentalStatusRequest(status.toUpperCase()));
  }

  return { status };
};

export default Books;
