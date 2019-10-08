import React from 'react';

import Nav from './Nav';

const Layout = ({ children }) => {
  return (
    <>
      <Nav />
      <main className='container'>{children}</main>
    </>
  );
};

export default Layout;
