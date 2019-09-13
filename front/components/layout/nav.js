import React from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { useRouter } from 'next/router';
import Link from 'next/link';

import { signOutRequest } from '@redux/actions/userActions';

import { HeaderNav, HomepageLink } from './Nav.styled';

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
        <li onClick={handleSignOut}>
          <a>로그아웃</a>
        </li>
      );
    }

    return (
      <div>
        <li>
          <Link href='/signin' prefetch={false}>
            <a>로그인</a>
          </Link>
        </li>
        <li>|</li>
        <li>
          <Link href='/join' prefetch={false}>
            <a>회원가입</a>
          </Link>
        </li>
      </div>
    );
  };

  return (
    <HeaderNav>
      <ul>
        <li>
          <Link href='/' prefetch={false}>
            <HomepageLink>
              <i className='material-icons'>share</i>
              <span>PublicShare</span>
            </HomepageLink>
          </Link>
        </li>
        {getSecondMenu()}
      </ul>
    </HeaderNav>
  );
};

export default Nav;
