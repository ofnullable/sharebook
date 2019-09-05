import React from 'react';

import BookCardWrapper from '@components/Book/BookCardWrapper';
import SearchBar from '@components/SearchBar';
import { loadBookListRequest } from '@redux/actions/bookActions';

const Home = () => (
  <div>
    <h1 className='title'>Welcome to Public Share!</h1>

    <SearchBar />

    <BookCardWrapper />
  </div>
);

Home.getInitialProps = async ctx => {
  ctx.store.dispatch(loadBookListRequest());
};

export default Home;
