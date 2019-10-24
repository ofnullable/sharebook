import React from 'react';

import LendingsPage from '@components/Management/LendingsPage';
import { loadLendingListRequest } from '@redux/actions/lendingActions';

const Lendings = ({ status }) => {
  return <LendingsPage status={status} />;
};

Lendings.getInitialProps = async ({ query, store }) => {
  const status = query.status;

  store.dispatch(loadLendingListRequest(status.toUpperCase()));

  return { status };
};

export default Lendings;
