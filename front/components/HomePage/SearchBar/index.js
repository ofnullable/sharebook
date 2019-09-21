import React from 'react';
import { useSelector } from 'react-redux';
import Router from 'next/router';

import { useInput } from '@utils/InputUtils';

import { SearchForm, SearchButton } from './index.styled';
import { SpinIcon } from '@styles/common';

const SearchBar = () => {
  const [searchText, handleSearchTextChange, setSearchText] = useInput();
  const { isLoading } = useSelector(state => state.book.list);

  const handleSearch = e => {
    e.preventDefault();
    if (!isLoading) {
      Router.push({ pathname: '/', query: { searchText } }, `/?searchText=${searchText}`);
      setSearchText('');
    }
  };

  return (
    <SearchForm onSubmit={handleSearch}>
      <input
        type='search'
        placeholder='제목으로 도서를 검색해보세요!'
        value={searchText}
        onChange={handleSearchTextChange}
      />
      <SearchButton _color='primary' className='searchButton'>
        {isLoading ? (
          <SpinIcon _size='16px' className='material-icons'>
            autorenew
          </SpinIcon>
        ) : (
          '검색'
        )}
      </SearchButton>
    </SearchForm>
  );
};

export default SearchBar;
