import React, { useContext } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { useRouter } from 'next/router';
import Link from 'next/link';

import { HamburgerContext } from '@utils/context';
import { signOutRequest } from '@redux/actions/userActions';

import { HeaderNav, HamburgerMenu, HeaderMenu, HeaderMenuGroup, HomepageLink } from './Nav.styled';

const Nav = () => {
  const { isSignedIn } = useSelector(state => state.user.user);
  const [_, setActive] = useContext(HamburgerContext);
  const dispatch = useDispatch();
  const router = useRouter();

  const openHamburgerMenu = () => {
    setActive(true);
  };

  const handleSignOut = () => {
    dispatch(signOutRequest());
  };

  const getSecondMenu = () => {
    if (router.pathname === '/signin' || router.pathname === '/join') {
      return;
    }

    if (isSignedIn) {
      return;
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
      <ul>
        <HamburgerMenu onClick={openHamburgerMenu}>
          <span />
          <span />
          <span />
        </HamburgerMenu>
        <HeaderMenu>
          <Link href='/' prefetch={false}>
            <HomepageLink>
              <i className='material-icons'>share</i>
              <span>PublicShare</span>
            </HomepageLink>
          </Link>
        </HeaderMenu>
        {getSecondMenu()}
        {isSignedIn && (
          <HeaderMenu _float='right'>
            <Link href='/settings/profile' prefetch={false}>
              <a>마이페이지</a>
            </Link>
          </HeaderMenu>
        )}
      </ul>
    </HeaderNav>
  );
};

export default Nav;
