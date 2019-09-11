import React from 'react';
import Router from 'next/router';

import { Category } from './CategoryButton.styled';

function CategoryButton({ name }) {
  const handleCategoryClick = () => {
    Router.push({ pathname: '/', query: { category: name } }, `/?category=${name}`);
  };

  return (
    <Category type='button' onClick={handleCategoryClick}>
      {name}
    </Category>
  );
}

export default CategoryButton;
