import React from 'react';
import { useDispatch, useSelector } from 'react-redux';

import { signOutRequest } from '@redux/actions/userActions';

import { SubMenu } from './index.styled';
import { Button } from '@styles/common';

const Profile = () => {
  const user = useSelector(state => state.user.user.data);
  const dispatch = useDispatch();

  const handleSignOut = () => {
    dispatch(signOutRequest());
  };

  return (
    <>
      <Button _color='gray' onClick={handleSignOut}>
        로그아웃
      </Button>
      <SubMenu>
        <span>프로필</span>
        <span>리뷰</span>
      </SubMenu>
    </>
  );
};

export default Profile;
