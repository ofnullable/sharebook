import React from 'react';
import Link from 'next/link';
import { useSelector } from 'react-redux';

import ManagementButton from './ManagementButton';
import BookCard from '@components/BookCard';
import LeftMenu from '@components/Management/LeftMenu';
import SubMenu from '@components/Management/SubMenu';
import LoadingOverlay from '@components/common/LoadingOverlay';

import { WithLeftMenu, Title } from '@components/Management/styled';
import { Button, CardWrapper, CenterDiv } from '@styles/common';

const SUBMENU = {
  all: '전체보기',
  requested: '요청받은도서',
  accepted: '대여해준도서',
};

const BooksPage = ({ status }) => {
  const { isLoading: isBooksLoading, data: books } = useSelector(state => state.book.myBooks);
  const { isLoading: isRequestsLoading, data: requests } = useSelector(
    state => state.lending.requests
  );

  const renderContents = () => {
    const data = status === 'all' ? books : requests;
    return data.length ? (
      status === 'all' ? (
        books.map(book => <BookCard key={book.id} data={book} />)
      ) : (
        requests.map(lending => (
          <BookCard key={lending.id} data={lending.book}>
            <ManagementButton status={status} lendingId={lending.id} />
          </BookCard>
        ))
      )
    ) : (
      <CenterDiv>
        <p>도서가 존재하지 않습니다.</p>
      </CenterDiv>
    );
  };

  return (
    <CenterDiv>
      <LeftMenu menu={'books'} />

      <WithLeftMenu>
        <Title>도서관리</Title>
        <Link href={`/management/books/register`}>
          <a>
            <Button _color='primary'>도서등록</Button>
          </a>
        </Link>
        <SubMenu href='/management/books/[status]' currentMenu={status} menus={SUBMENU} />

        {(isBooksLoading || isRequestsLoading) && <LoadingOverlay />}

        <CardWrapper>{renderContents()}</CardWrapper>
      </WithLeftMenu>
    </CenterDiv>
  );
};

export default BooksPage;
