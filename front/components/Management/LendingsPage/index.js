import React from 'react';
import { useSelector } from 'react-redux';

import LeftMenu from '@components/Management/LeftMenu';
import SubMenu from '@components/Management/SubMenu';
import LendingHistory from '@components/Management/LendingsPage/LendingHistory';
import LoadingOverlay from '@components/common/LoadingOverlay';

import { WithLeftMenu, Title } from '@components/Management/styled';
import { CardWrapper, CenterDiv } from '@styles/common';

const SUBMENU = {
  accepted: '대여중도서',
  requested: '요청중도서',
  returned: '대여기록',
};

const LendingsPage = ({ status }) => {
  const { isLoading, data } = useSelector(state => state.lending.myRequests);

  return (
    <CenterDiv>
      <LeftMenu menu={'lendings'} />

      <WithLeftMenu>
        <Title>대여관리</Title>
        <SubMenu href='/management/lendings/[status]' currentMenu={status} menus={SUBMENU} />

        {isLoading && <LoadingOverlay />}

        <CardWrapper>
          {!isLoading &&
            (data.length ? (
              data.map(lending => <LendingHistory key={lending.id} data={lending} />)
            ) : (
              <CenterDiv>
                <p>기록이 존재하지 않습니다.</p>
              </CenterDiv>
            ))}
        </CardWrapper>
      </WithLeftMenu>
    </CenterDiv>
  );
};

export default LendingsPage;
