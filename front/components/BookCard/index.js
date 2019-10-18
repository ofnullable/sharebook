import React from 'react';
import Link from 'next/link';

import { Card, CardBody } from './index.styled';

const BookCard = ({ data, children }) => {
  return (
    <Link
      href={{ pathname: '/book', query: { id: data.id } }}
      as={`/book/${data.id}`}
      prefetch={false}
    >
      <a>
        <Card>
          <img src={`${data.imageUrl}`} />
          {children}
        </Card>
      </a>
    </Link>
  );
};

export default BookCard;
