import React from 'react';
import Link from 'next/link';

import { Category } from './index.styled';

const CategoryButton = ({ name }) => {
  return (
    <Link href={`/?category=${name}`} prefetch={false}>
      <a>
        <Category type='button'>{name}</Category>
      </a>
    </Link>
  );
};

export default CategoryButton;
