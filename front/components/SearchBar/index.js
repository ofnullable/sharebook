import React from 'react';
import { useDispatch, useSelector } from 'react-redux';

import { useInput } from '@components/utils/InputUtils';
import { loadBookListRequest } from '@redux/actions/bookActions';

import { SearchForm } from './index.styled';
import { Button, SpinIcon } from '@styles/global';

function SearchBar() {
  const [searchText, handleSearchTextChange] = useInput();
  const { isLoading } = useSelector(state => state.book.list);
  const dispatch = useDispatch();

  const handleSearch = e => {
    e.preventDefault();
    if (!isLoading) {
      dispatch(loadBookListRequest(1, 10, searchText));
    }
  };

  return (
    <SearchForm onSubmit={handleSearch}>
      <input type='text' value={searchText} onChange={handleSearchTextChange}></input>
      <Button _color='primary'>
        {isLoading ? (
          <SpinIcon _size='16px' className='material-icons'>
            autorenew
          </SpinIcon>
        ) : (
          '검색'
        )}
      </Button>
    </SearchForm>
  );
}

export default SearchBar;
