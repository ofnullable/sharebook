import React from 'react';

import HomePage from '@components/HomePage';
import { loadCategoryListRequest } from '@redux/actions/categoryActions';
import { loadBookListRequest, loadBookListByCategoryRequest } from '@redux/actions/bookActions';

const Home = ({ category }) => {
  return <HomePage category={category} />;
};

Home.getInitialProps = async ({ query, store }) => {
  const categoryList = store.getState().category.list.data;
  if (!categoryList.length) {
    store.dispatch(loadCategoryListRequest());
  }

  const searchText = query.searchText;
  if (searchText) {
    store.dispatch(loadBookListRequest(searchText));
    return { category: null };
  }
  const category = query.category;
  if (!category || category === 'ALL') {
    store.dispatch(loadBookListRequest());
    return { category: null };
  } else {
    store.dispatch(loadBookListByCategoryRequest(category));
    return { category };
  }
};

export default Home;
