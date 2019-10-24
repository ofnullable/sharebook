import React, { useEffect } from 'react';
import { useSelector } from 'react-redux';
import { useRouter } from 'next/router';
import Link from 'next/link';

import { MenuWrapper, MenuItem } from './LeftMenu.styled';

const menus = {
  profile: {
    name: '개인관리',
    icon: 'account_circle',
    pathname: '/management/user',
    query: { menu: 'profile' },
    as: '/management/user/profile',
  },
  books: {
    name: '도서관리',
    icon: 'menu_book',
    pathname: '/management/books',
    query: { status: '' },
    as: '/management/books/',
  },
  lendings: {
    name: '대여관리',
    icon: 'list_alt',
    pathname: '/management/lendings',
    query: { status: 'accepted' },
    as: '/management/lendings/accepted',
  },
};
const activeStyle = { backgroundColor: '#e9ecef' };

const LeftMenu = ({ menu }) => {
  const { isSignedIn, isLoading } = useSelector(state => state.user.user);
  const router = useRouter();

  useEffect(() => {
    if (!isLoading && !isSignedIn) {
      router.push('/signin');
    }
  }, [isLoading, isSignedIn]);

  return (
    <MenuWrapper>
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
    </MenuWrapper>
  );
};

export default LeftMenu;
