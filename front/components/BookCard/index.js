import React from 'react';
import Link from 'next/link';
import moment from 'moment';

import { IMAGE_BASE_URL } from '@utils/consts';

import { Card, CardBody } from './index.styled';

const BookCard = ({ data, children }) => {
  return (
    <Card>
      <Link href='/book/[id]' as={`/book/${data.id}`}>
        <a>
          <img src={`${IMAGE_BASE_URL}${data.imageUri}`} />
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
