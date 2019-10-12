import React from 'react';

import RentalsPage from '@components/Management/RentalsPage';
import { loadRentalListRequest } from '@redux/actions/rentalActions';

const Rentals = ({ status }) => {
  return <RentalsPage status={status} />;
};

Rentals.getInitialProps = async ({ query, store }) => {
  const status = query.status;

  store.dispatch(loadRentalListRequest(status.toUpperCase()));

  return { status };
};

export default Rentals;
