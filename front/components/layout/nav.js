import React from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { useRouter } from 'next/router';
import Link from 'next/link';

import { signOutRequest } from '@redux/actions/userActions';

import { HeaderNav, HeaderMenu, HeaderMenuGroup, HomepageLink } from './Nav.styled';

const Nav = () => {
  const { isSignedIn } = useSelector(state => state.user.user);
  const dispatch = useDispatch();
  const router = useRouter();

  const handleSignOut = () => {
    dispatch(signOutRequest());
  };

  const getSecondMenu = () => {
    if (router.pathname === '/signin' || router.pathname === '/join') {
      return;
    }

    if (isSignedIn) {
      return (
        <HeaderMenu onClick={handleSignOut} _float='right'>
          <a>로그아웃</a>
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
      <ul>
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
            <Link href='/mypage' prefetch={false}>
              <a>마이페이지</a>
            </Link>
          </HeaderMenu>
        )}
      </ul>
    </HeaderNav>
  );
};

export default Nav;
