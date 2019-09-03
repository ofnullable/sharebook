import React from 'react';
import { useDispatch, useSelector } from 'react-redux';
import Link from 'next/link';
import { useRouter } from 'next/router';

import { signOutRequest } from '@redux/actions/userActions';

import { HeaderNav, Homepage } from './Nav.styled';

const Nav = () => {
  const { isSignedIn } = useSelector(state => state.user);
  const dispatch = useDispatch();
  const router = useRouter();

  const handleSignOut = () => {
    dispatch(signOutRequest());
  };

  const renderSecondMenu = () => {
    // better way..
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

  return (
    <HeaderNav>
      <ul>
        <li>
          <Link href='/' prefetch={false}>
            <Homepage>
              <i className='material-icons'>share</i>
              <span>PublicShare</span>
            </Homepage>
          </Link>
        </li>
        {renderSecondMenu()}
      </ul>
    </HeaderNav>
  );
};

export default Nav;
