import React, { useEffect } from 'react';
import Link from 'next/link';
import { useRouter } from 'next/router';
import { useSelector } from 'react-redux';

import Profile from './Profile';
import Books from './Books';
import Rentals from './Rentals';

import { LeftMenu, MenuItem, WithLeftMenu, Title } from './index.styled';
import { CenterDiv } from '@styles/common';

const menus = {
  profile: {
    name: '개인관리',
    icon: 'account_circle',
    as: '/settings/profile',
    pathname: '/settings',
    query: { menu: 'profile' },
    component: <Profile />,
  },
  books: {
    name: '도서관리',
    icon: 'menu_book',
    as: '/settings/books/',
    pathname: '/management/books',
    query: { status: '' },
    component: <Profile />,
  },
  rentals: {
    name: '대여관리',
    icon: 'list_alt',
    as: '/settings/rentals/',
    pathname: '/settings',
    query: { menu: 'rentals' },
    component: <Rentals />,
  },
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
        <ul>
          {Object.keys(menus).map(m => {
            return (
              <Link
                key={m}
                href={{ pathname: menus[m].pathname, query: menus[m].query }}
                as={menus[m].as}
                prefetch={false}
              >
                <MenuItem style={menu === m ? activeStyle : {}}>
                  <i className='material-icons'>{menus[m].icon}</i>
                  <p>{menus[m].name}</p>
                </MenuItem>
              </Link>
            );
          })}
        </ul>
      </LeftMenu>
      <WithLeftMenu>
        <Title>{menus[menu].name}</Title>
        {menus[menu].component}
      </WithLeftMenu>
    </CenterDiv>
  );
};

export default SettingsPage;
