import React from 'react';

import { loadMyBookListRequest } from '@redux/actions/bookActions';

import SettingsPage from '@components/SettingsPage';

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
      // load rentals my user id
      break;
  }

  return { menu };
};

export default Settings;
