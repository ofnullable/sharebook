import React from 'react';
import { useSelector } from 'react-redux';
import Router from 'next/router';

import CategoryButton from '@components/SearchBar/CategoryButton';
import { useInput } from '@utils/InputUtils';

import { SearchForm, SearchButton } from './index.styled';
import { SpinIcon } from '@styles/global';

function SearchBar() {
  const [searchText, handleSearchTextChange, setSearchText] = useInput();
  const { isLoading } = useSelector(state => state.book.list);
  const categoryList = useSelector(state => state.category.list);

  const handleSearch = e => {
    e.preventDefault();
    if (!isLoading) {
      Router.push({ pathname: '/', query: { searchText } }, `/?searchText=${searchText}`);
      setSearchText('');
    }
  };

  return (
    <SearchForm onSubmit={handleSearch}>
      <input type='text' value={searchText} onChange={handleSearchTextChange} />
      <SearchButton _color='primary' className='searchButton'>
        {isLoading ? (
          <SpinIcon _size='16px' className='material-icons'>
            autorenew
          </SpinIcon>
        ) : (
          '검색'
        )}
      </SearchButton>
      {!categoryList.isLoading && [
        <CategoryButton key='ALL' name='ALL' />,
        ...categoryList.data.map(c => <CategoryButton key={c.id} name={c.name} />),
      ]}
    </SearchForm>
  );
}

export default SearchBar;
