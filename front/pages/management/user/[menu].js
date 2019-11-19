import React from 'react';

import UserPage from '@components/Management/UserPage';
import { loadMyReviewListRequest } from '@redux/actions/reviewActions';

const User = ({ menu }) => {
  return <UserPage menu={menu} />;
};

User.getInitialProps = async ({ query, store }) => {
  const menu = query.menu;

  if (menu === 'reviews') {
    store.dispatch(loadMyReviewListRequest());
  }

  return { menu };
};

export default User;
