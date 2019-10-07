import React from 'react';

import {
  loadMyBookListRequest,
  loadMyBookListByRentalStatusRequest,
} from '@redux/actions/bookActions';
import SettingsPage from '@components/SettingsPage';
import { RENTAL_STATUS } from '@utils/consts';

const Settings = ({ menu }) => {
  return <SettingsPage menu={menu} />;
};

Settings.getInitialProps = async ({ query, store }) => {
  const menu = query.menu;

  switch (menu) {
    case 'books':
      store.dispatch(loadMyBookListRequest());
      break;
    case 'rentals':
      store.dispatch(loadMyBookListByRentalStatusRequest(RENTAL_STATUS.REQUESTED));
      break;
  }

  return { menu };
};

export default Settings;
