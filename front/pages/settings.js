import React, { useEffect } from 'react';
import { useRouter } from 'next/router';
import { useSelector } from 'react-redux';

import {
  loadMyBookListRequest,
  loadMyBookListByRentalStatusRequest,
} from '@redux/actions/bookActions';
import SettingsPage from '@components/SettingsPage';
import { RENTAL_STATUS } from '@utils/consts';

const Settings = ({ menu }) => {
  const { isSignedIn, isLoading } = useSelector(state => state.user.user);
  const router = useRouter();

  useEffect(() => {
    if (!isLoading && !isSignedIn) {
      router.push('/signin');
    }
  }, [isLoading, isSignedIn]);

  return <SettingsPage menu={menu} />;
};

Settings.getInitialProps = async ({ query, store }) => {
  const menu = query.menu;

  // store.dispatch({});
  switch (menu) {
    case 'books':
      store.dispatch(loadMyBookListRequest());
      break;
    case 'rentals':
      // load rentals my user id
      store.dispatch(loadMyBookListByRentalStatusRequest(RENTAL_STATUS.REQUESTED));
      break;
  }

  return { menu };
};

export default Settings;
