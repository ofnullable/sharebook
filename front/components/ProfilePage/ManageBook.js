import React from 'react';
import { useSelector } from 'react-redux';

const ManageBook = () => {
  const myBookList = useSelector(state => state.book.myBookList.data);
  return (
    <div>
      <h1>도서관리</h1>
    </div>
  );
};

export default ManageBook;
