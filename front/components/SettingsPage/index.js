import React, { useEffect } from 'react';
import Link from 'next/link';
import { useRouter } from 'next/router';
import { useSelector } from 'react-redux';

import Profile from './Profile';
import Books from './Books';
import Rentals from './Rentals';

import { LeftMenu, MenuItem, WithLeftMenu, BottomMenuBar, BottomMenuItem } from './index.styled';
import { CenterDiv } from '@styles/common';

const menus = {
  profile: { name: '개인관리', icon: 'account_circle', component: <Profile /> },
  books: { name: '도서관리', icon: 'menu_book', component: <Books /> },
  rentals: { name: '대여관리', icon: 'list_alt', component: <Rentals /> },
};
const activeStyle = { backgroundColor: '#e9ecef' };

const SettingsPage = ({ menu }) => {
  const { isSignedIn, isLoading } = useSelector(state => state.user.user);
  const router = useRouter();

  useEffect(() => {
    if (!isLoading && !isSignedIn) {
      router.push('/signin');
    }
  }, [isLoading, isSignedIn]);

  return (
    <CenterDiv>
      <LeftMenu>
        <h2>관리</h2>
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
      <WithLeftMenu>{menus[menu].component}</WithLeftMenu>
      <BottomMenuBar>
        {Object.keys(menus).map(m => {
          return (
            <Link key={m} href={`/settings?menu=${m}`} as={`/settings/${m}`} prefetch={false}>
              <BottomMenuItem style={menu === m ? activeStyle : {}}>
                <i className='material-icons'>{menus[m].icon}</i>
                <p>{menus[m].name}</p>
              </BottomMenuItem>
            </Link>
          );
        })}
      </BottomMenuBar>
    </CenterDiv>
  );
};

export default SettingsPage;
