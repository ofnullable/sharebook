import React from 'react';

import RentalsPage from '@components/Management/RentalsPage';
import { RENTAL_STATUS } from '@utils/consts';

const Rentals = ({ status }) => {
  return <RentalsPage status={status} />;
};

Rentals.getInitialProps = async ({ query, store }) => {
  const status = query.status;

  return { status };
};

export default Rentals;
