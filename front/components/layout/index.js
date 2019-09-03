import React from 'react';

import Nav from './nav';

function Layout({ children }) {
  return (
    <>
      <Nav />
      <main className='container'>{children}</main>
    </>
  );
}

export default Layout;
