import React from 'react';

import UserPage from '@components/Management/UserPage';

const User = ({ menu }) => {
  return <UserPage menu={menu} />;
};

User.getInitialProps = async ({ query, store }) => {
  const menu = query.menu;
  return { menu };
};

export default User;
