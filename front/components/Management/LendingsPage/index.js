import React from 'react';
import Link from 'next/link';
import { useSelector } from 'react-redux';

import LeftMenu from '@components/Management/LeftMenu';
import LendingHistory from '@components/Management/LendingsPage/LendingHistory';

import { WithLeftMenu, Title, SubMenu } from '@components/Management/styled';
import {
  CardWrapper,
  CenterDiv,
  SpinIcon,
  ScreenOverlay,
  LoadingIconWrapper,
} from '@styles/common';

const LendingsPage = ({ status }) => {
  const { isLoading, data } = useSelector(state => state.lending.myLendings);

  const renderSubMenu = () => {
    return (
      <SubMenu>
        <Link
          href={{ pathname: `/management/lendings`, query: { status: 'accepted' } }}
          as={`/management/lendings/accepted`}
        >
          <a>
            <span className={status === 'accepted' ? 'active' : ''}>대여중도서</span>
          </a>
        </Link>
        <Link
          href={{ pathname: `/management/lendings`, query: { status: 'requested' } }}
          as={`/management/lendings/requested`}
        >
          <a>
            <span className={status === 'requested' ? 'active' : ''}>요청중도서</span>
          </a>
        </Link>
        <Link
          href={{ pathname: `/management/lendings`, query: { status: 'returned' } }}
          as={`/management/lendings/returned`}
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
      <LeftMenu menu={'lendings'} />

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
              data.map(lending => <LendingHistory key={lending.id} data={lending} />)
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

export default LendingsPage;
