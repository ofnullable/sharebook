import React from 'react';
import Link from 'next/link';
import moment from 'moment';

import { Card, CardBody } from './index.styled';

const BookCard = ({ data, children }) => {
  return (
    <Card>
      <Link href='/book/[id]' as={`/book/${data.id}`}>
        <a>
          <img src={`${data.imageUrl}?v=${data.id}`} />
        </a>
      </Link>
      <CardBody>
        <h2 className='card-title'>{data.title}</h2>
        <p className='description'>{`${data.category} | ${data.author} | ${data.publisher}`}</p>
        <span>{moment(data.createdAt).format('YYYY.MM.DD')}</span>
        {children}
      </CardBody>
    </Card>
  );
};

export default BookCard;
