import React, { useEffect } from 'react';
import { useSelector } from 'react-redux';
import { useRouter } from 'next/router';
import Link from 'next/link';

import { MenuWrapper, MenuItem } from './LeftMenu.styled';

const menus = {
  profile: {
    name: '개인관리',
    icon: 'account_circle',
    pathname: '/management/user/[menu]',
    as: '/management/user/profile',
  },
  books: {
    name: '도서관리',
    icon: 'menu_book',
    pathname: '/management/books/[status]',
    as: '/management/books/all',
  },
  lendings: {
    name: '대여관리',
    icon: 'list_alt',
    pathname: '/management/lendings/[status]',
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
            <Link key={m} href={`${menus[m].pathname}`} as={menus[m].as}>
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
