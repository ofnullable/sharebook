import React from 'react';

import Nav from './Nav';

import { HamburgerProvider } from '@utils/context';

const Layout = ({ children }) => {
  return (
    <>
      <HamburgerProvider>
        <Nav />
        <main className='container'>{children}</main>
      </HamburgerProvider>
    </>
  );
};

export default Layout;
