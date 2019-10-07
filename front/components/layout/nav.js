import React, { useContext } from 'react';
import { useSelector } from 'react-redux';
import { useRouter } from 'next/router';
import Link from 'next/link';

import { HamburgerContext } from '@utils/context';

import { HeaderNav, HamburgerMenu, HeaderMenu, HeaderMenuGroup, HomepageLink } from './Nav.styled';

const Nav = () => {
  const { isSignedIn } = useSelector(state => state.user.user);
  const [_, setActive] = useContext(HamburgerContext);
  const router = useRouter();

  const openHamburgerMenu = () => {
    setActive(true);
  };

  const getSecondMenu = () => {
    if (router.pathname === '/signin' || router.pathname === '/join') {
      return;
    }

    if (isSignedIn) {
      return (
        <HeaderMenu _float='right'>
          <Link href='/settings/profile' prefetch={false}>
            <a>마이페이지</a>
          </Link>
        </HeaderMenu>
      );
    }

    return (
      <HeaderMenu _float='right'>
        <HeaderMenuGroup>
          <Link href='/signin' prefetch={false}>
            <a>로그인</a>
          </Link>
          <span>|</span>
          <Link href='/join' prefetch={false}>
            <a>회원가입</a>
          </Link>
        </HeaderMenuGroup>
      </HeaderMenu>
    );
  };

  return (
    <HeaderNav>
      <ul className='container'>
        {router.pathname.startsWith('/settings') && (
          <HamburgerMenu onClick={openHamburgerMenu}>
            <span />
            <span />
            <span />
          </HamburgerMenu>
        )}
        <HeaderMenu>
          <Link href='/' prefetch={false}>
            <HomepageLink>
              <i className='material-icons'>share</i>
              <span>PublicShare</span>
            </HomepageLink>
          </Link>
        </HeaderMenu>
        {getSecondMenu()}
      </ul>
    </HeaderNav>
  );
};

export default Nav;
