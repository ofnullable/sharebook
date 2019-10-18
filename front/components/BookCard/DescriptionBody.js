import React from 'react';

import { CardBody } from './index.styled';

const DescriptionBody = ({ data }) => {
  return (
    <CardBody>
      <h2 className='card-title'>{data.title}</h2>
      <p className='description'>{`${data.category} | ${data.author} | ${data.publisher}`}</p>
      <span>{data.createdAt}</span>
    </CardBody>
  );
};

export default DescriptionBody;
