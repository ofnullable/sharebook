import React from 'react';
import Link from 'next/link';
import { useRouter } from 'next/router';
import { useSelector } from 'react-redux';

import BookCard from '@components/BookCard';

import { SubMenu } from './index.styled';
import {
  Button,
  CardWrapper,
  CenterDiv,
  SpinIcon,
  ScreenOverlay,
  LoadingIconWrapper,
} from '@styles/common';

const Books = ({ status }) => {
  const { isLoading, data } = useSelector(state => state.book.myBooks);
  const router = useRouter();

  const renderSubMenu = () => {
    return (
      <SubMenu>
        <Link href={`/settings?menu=books&status=`} as={`/settings/books/`}>
          <a>
            <span className={!status ? 'active' : ''}>전체보기</span>
          </a>
        </Link>
        <Link href={`/settings?menu=books&status=requested`} as={`/settings/books/requested`}>
          <a>
            <span className={status === 'REQUESTED' ? 'active' : ''}>요청받은도서</span>
          </a>
        </Link>
        <Link href={`/settings?menu=books&status=accepted`} as={`/settings/books/accepted`}>
          <a>
            <span className={status === 'ACCEPTED' ? 'active' : ''}>대여해준도서</span>
          </a>
        </Link>
      </SubMenu>
    );
  };

  return (
    <>
      <Button _color='primary' onClick={() => router.push('/settings/books/register')}>
        도서등록
      </Button>
      {renderSubMenu()}

      {isLoading && (
        <>
          <ScreenOverlay />
          <LoadingIconWrapper>
            <SpinIcon _size='100px' _color='primary' className='material-icons'>
              autorenew
            </SpinIcon>
          </LoadingIconWrapper>
        </>
      )}

      <CardWrapper>
        {!isLoading &&
          (data.length ? (
            data.map(d => <BookCard key={d.id} data={d} />)
          ) : (
            <CenterDiv>
              <p>도서가 존재하지 않습니다.</p>
            </CenterDiv>
          ))}
      </CardWrapper>
    </>
  );
};

export default Books;
