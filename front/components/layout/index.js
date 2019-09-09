import React from 'react';

import Nav from './Nav';

function Layout({ children }) {
  return (
    <>
      <Nav />
      <main className='container'>{children}</main>
    </>
  );
}

export default Layout;
