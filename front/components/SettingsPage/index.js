import React, { useContext } from 'react';
import Link from 'next/link';

import Profile from './Profile';
import Books from './Books';
import Rentals from './Rentals';
import { HamburgerContext } from '@utils/context';

import { LeftMenu, MenuItem, MenuCloseArea, WithLeftMenu } from './index.styled';
import { CenterDiv } from '@styles/common';

const menus = {
  profile: { name: '프로필', component: <Profile /> },
  books: { name: '도서관리', component: <Books /> },
  rentals: { name: '대여목록', component: <Rentals /> },
};
const activeStyle = { backgroundColor: '#e9ecef' };

const SettingsPage = ({ menu }) => {
  const [active, setActive] = useContext(HamburgerContext);

  const handleMenuClose = () => {
    setActive(false);
  };

  return (
    <CenterDiv>
      <LeftMenu className={active ? 'active' : ''} onClick={handleMenuClose}>
        <h2>Settings</h2>
        <ul>
          {Object.keys(menus).map(m => {
            return (
              <Link key={m} href={`/settings?menu=${m}`} as={`/settings/${m}`} prefetch={false}>
                <MenuItem style={menu === m ? activeStyle : {}}>{menus[m].name}</MenuItem>
              </Link>
            );
          })}
        </ul>
      </LeftMenu>
      <MenuCloseArea onClick={handleMenuClose} />
      <WithLeftMenu>{menus[menu].component}</WithLeftMenu>
    </CenterDiv>
  );
};

export default SettingsPage;
