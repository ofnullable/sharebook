import React from 'react';
import Link from 'next/link';
import { useRouter } from 'next/router';
import { useSelector } from 'react-redux';

import BookCard from '@components/BookCard';
import LeftMenu from '@components/Management/LeftMenu';
import LoadingOverlay from '@components/common/LoadingOverlay';

import { WithLeftMenu, Title, SubMenu } from '@components/Management/styled';
import { Button, CardWrapper, CenterDiv } from '@styles/common';

const BooksPage = ({ status }) => {
  const { isLoading, data } = useSelector(state => state.book.myBooks);
  const router = useRouter();

  const renderSubMenu = () => {
    return (
      <SubMenu>
        <Link href='/management/books/[status]' as={`/management/books/all`}>
          <a>
            <span className={status === 'all' ? 'active' : ''}>전체보기</span>
          </a>
        </Link>
        <Link href='/management/books/[status]' as={`/management/books/requested`}>
          <a>
            <span className={status === 'requested' ? 'active' : ''}>요청받은도서</span>
          </a>
        </Link>
        <Link href='/management/books/[status]' as={`/management/books/accepted`}>
          <a>
            <span className={status === 'accepted' ? 'active' : ''}>대여해준도서</span>
          </a>
        </Link>
      </SubMenu>
    );
  };

  return (
    <CenterDiv>
      <LeftMenu menu={'books'} />

      <WithLeftMenu>
        <Title>도서관리</Title>
        <Button _color='primary' onClick={() => router.push('/management/books/register')}>
          도서등록
        </Button>

        {renderSubMenu()}

        {isLoading && <LoadingOverlay />}

        <CardWrapper>
          {!isLoading &&
            (data.length ? (
              data.map(book => <BookCard key={book.id} data={book} />)
            ) : (
              <CenterDiv>
                <p>도서가 존재하지 않습니다.</p>
              </CenterDiv>
            ))}
        </CardWrapper>
      </WithLeftMenu>
    </CenterDiv>
  );
};

export default BooksPage;
