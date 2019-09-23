import React from 'react';
import { useSelector } from 'react-redux';

import { Title } from './index.styled';

const Profile = () => {
  const user = useSelector(state => state.user.user.data);
  return (
    <>
      <Title>내 프로필</Title>
    </>
  );
};

export default Profile;
