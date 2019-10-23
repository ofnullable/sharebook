import React from 'react';

import {
  loadMyBookListRequest,
  loadMyBookListByLendingStatusRequest,
} from '@redux/actions/bookActions';
import SettingsPage from '@components/SettingsPage';
import { LENDING_STATUS } from '@utils/consts';

const Settings = ({ menu }) => {
  return <SettingsPage menu={menu} />;
};

Settings.getInitialProps = async ({ query, store }) => {
  const menu = query.menu;

  switch (menu) {
    case 'books':
      store.dispatch(loadMyBookListRequest());
      break;
    case 'lendings':
      store.dispatch(loadMyBookListByLendingStatusRequest(LENDING_STATUS.REQUESTED));
      break;
  }

  return { menu };
};

export default Settings;
