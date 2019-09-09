import React from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { useRouter } from 'next/router';
import Link from 'next/link';

import { signOutRequest } from '@redux/actions/userActions';

import { HeaderNav, HomepageLink, Homepage } from './Nav.styled';

const Nav = () => {
  const { isSignedIn } = useSelector(state => state.user);
  const dispatch = useDispatch();
  const router = useRouter();

  const handleSignOut = () => {
    dispatch(signOutRequest());
  };

  const getSecondMenu = () => {
    if (router.pathname === '/signin') {
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
      <li>
        <Link href='/signin' prefetch={false}>
          <a>로그인</a>
        </Link>
      </li>
    );
  };

  const renderMenu = () => {
    const secondMenu = getSecondMenu();
    if (router.pathname === '/') {
      return (
        <ul>
          <li>
            <Homepage>
              <i className='material-icons'>share</i>
              <span>PublicShare</span>
            </Homepage>
          </li>
          {secondMenu}
        </ul>
      );
    }
    return (
      <ul>
        <li>
          <Link href='/' prefetch={false}>
            <HomepageLink>
              <i className='material-icons'>share</i>
              <span>PublicShare</span>
            </HomepageLink>
          </Link>
        </li>
        {secondMenu}
      </ul>
    );
  };

  return <HeaderNav>{renderMenu()}</HeaderNav>;
};

export default Nav;
