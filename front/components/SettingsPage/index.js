import React, { useEffect } from 'react';
import { useSelector } from 'react-redux';
import { useRouter } from 'next/router';
import Link from 'next/link';

import Profile from './Profile';
import Books from './Books';
import Rentals from './Rentals';

import { LeftMenu, MenuItem, WithLeftMenu } from './index.styled';
import { CenterDiv } from '@styles/common';

const menus = {
  profile: { name: '프로필', component: <Profile /> },
  books: { name: '도서관리', component: <Books /> },
  rentals: { name: '대여목록', component: <Rentals /> },
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
        <h2>My page</h2>
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
    </CenterDiv>
  );
};

export default SettingsPage;
