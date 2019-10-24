import React from 'react';
import { useDispatch } from 'react-redux';

import { signOutRequest } from '@redux/actions/userActions';
import LeftMenu from '@components/Management/LeftMenu';

import { WithLeftMenu, Title, SubMenu } from '@components/Management/styled';
import { Button, CenterDiv } from '@styles/common';
import Link from 'next/link';

const UserPage = ({ menu }) => {
  const dispatch = useDispatch();

  const renderSubMenu = () => {
    return (
      <SubMenu>
        <Link
          href={{ pathname: `/management/user`, query: { menu: 'profile' } }}
          as={`/management/user/profile`}
        >
          <a>
            <span className={menu === 'profile' ? 'active' : ''}>프로필</span>
          </a>
        </Link>
        <Link
          href={{ pathname: `/management/user`, query: { menu: 'reviews' } }}
          as={`/management/user/reviews`}
        >
          <a>
            <span className={menu === 'reviews' ? 'active' : ''}>리뷰관리</span>
          </a>
        </Link>
      </SubMenu>
    );
  };

  const handleSignOut = () => {
    dispatch(signOutRequest());
  };

  return (
    <CenterDiv>
      <LeftMenu menu={'profile'} />

      <WithLeftMenu>
        <Title>개인관리</Title>
        <Button _color='gray' onClick={handleSignOut}>
          로그아웃
        </Button>
        {renderSubMenu()}
      </WithLeftMenu>
    </CenterDiv>
  );
};

export default UserPage;
