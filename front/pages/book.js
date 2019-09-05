import React from 'react';
import { useSelector } from 'react-redux';

import { loadBookRequest } from '@redux/actions/bookActions';

const Book = ({ id }) => {
  const { data } = useSelector(state => state.book.detail);

  return (
    <div>
      <h1 className='title'>{data.title}</h1>
      <div>
        <img src={`https://placeimg.com/540/500/animals`} alt='' style={{ display: 'block' }} />
      </div>
      <p>{`${data.author} | ${data.publisher}`}</p>
      <span>{data.createdAt}</span>
      <div>{data.description}</div>
    </div>
  );
};

Book.getInitialProps = async ctx => {
  const bookId = ctx.query.id;
  ctx.store.dispatch(loadBookRequest(bookId));
  return { id: bookId };
};

export default Book;
