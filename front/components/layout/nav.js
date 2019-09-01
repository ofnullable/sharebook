import React from 'react';
import Link from 'next/link';
import { useRouter } from 'next/router';

import { HeaderNav } from './nav.styled';

const Nav = () => {
  const router = useRouter();

  const renderSecondMenu = () => {
    // better way..
    if (router.pathname === '/signin') {
      return (
        <li>
          <Link href='/join'>
            <a>Sign Up</a>
          </Link>
        </li>
      );
    }
    return (
      <li>
        <Link href='/signin'>
          <a>Sign In</a>
        </Link>
      </li>
    );
  };

  return (
    <HeaderNav>
      <ul>
        <li>
          <Link href='/'>
            <a>Home</a>
          </Link>
        </li>
        {renderSecondMenu()}
      </ul>
    </HeaderNav>
  );
};

export default Nav;
