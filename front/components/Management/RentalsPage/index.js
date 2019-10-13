import React from 'react';
import Link from 'next/link';
import { useSelector } from 'react-redux';

import BookCard from '@components/BookCard';
import LeftMenu from '@components/Management/LeftMenu';

import { WithLeftMenu, Title, SubMenu } from '@components/Management/styled';
import {
  CardWrapper,
  CenterDiv,
  SpinIcon,
  ScreenOverlay,
  LoadingIconWrapper,
} from '@styles/common';

const RentalPage = ({ status }) => {
  const { isLoading, data } = useSelector(state => state.rental.myRentals);

  const renderSubMenu = () => {
    return (
      <SubMenu>
        <Link
          href={{ pathname: `/management/rentals`, query: { status: 'accepted' } }}
          as={`/settings/rentals/accepted`}
        >
          <a>
            <span className={status === 'accepted' ? 'active' : ''}>대여중도서</span>
          </a>
        </Link>
        <Link
          href={{ pathname: `/management/rentals`, query: { status: 'requested' } }}
          as={`/settings/rentals/requested`}
        >
          <a>
            <span className={status === 'requested' ? 'active' : ''}>요청한도서</span>
          </a>
        </Link>
        <Link
          href={{ pathname: `/management/rentals`, query: { status: 'returned' } }}
          as={`/settings/rentals/returned`}
        >
          <a>
            <span className={status === 'returned' ? 'active' : ''}>대여기록</span>
          </a>
        </Link>
      </SubMenu>
    );
  };

  return (
    <CenterDiv>
      <LeftMenu menu={'rentals'} />

      <WithLeftMenu>
        <Title>대여관리</Title>
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
              data.map(d => <BookCard key={d.id} data={d.book} />)
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

export default RentalPage;
