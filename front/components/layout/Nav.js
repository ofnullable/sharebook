import React from 'react';
import { useSelector } from 'react-redux';
import { useRouter } from 'next/router';
import Link from 'next/link';

import { HeaderNav, HeaderMenu, HeaderMenuGroup, HomepageLink } from './Nav.styled';

const Nav = () => {
  const { data, isSignedIn } = useSelector(state => state.user.user);
  const router = useRouter();

  const getSecondMenu = () => {
    if (
      ['/signin', '/join'].includes(router.pathname) ||
      router.pathname.startsWith('/management')
    ) {
      return;
    }

    if (isSignedIn) {
      return (
        <HeaderMenu _float='right'>
          <Link href='/management/user/[menu]' as='/management/user/profile'>
            <a>
              <i className='material-icons'>perm_identity</i>
              <span>{data.name}</span>
            </a>
          </Link>
        </HeaderMenu>
      );
    }

    return (
      <HeaderMenu _float='right'>
        <HeaderMenuGroup>
          <Link href={{ pathname: '/signin' }}>
            <a>
              <span>로그인</span>
            </a>
          </Link>
          <span style={{ padding: '0 10px' }}>|</span>
          <Link href={{ pathname: '/join' }}>
            <a>
              <span>회원가입</span>
            </a>
          </Link>
        </HeaderMenuGroup>
      </HeaderMenu>
    );
  };

  return (
    <>
      <HeaderNav>
        <ul className='container'>
          <HeaderMenu>
            <Link href='/'>
              <HomepageLink>
                <i className='material-icons'>share</i>
                <span>SHAREBOOK</span>
              </HomepageLink>
            </Link>
          </HeaderMenu>
          {getSecondMenu()}
        </ul>
      </HeaderNav>
    </>
  );
};

export default Nav;
