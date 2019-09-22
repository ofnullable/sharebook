import React from 'react';
import { useSelector } from 'react-redux';

const Profile = () => {
  const user = useSelector(state => state.user.user.data);
  return (
    <>
      <h1>내 프로필</h1>
    </>
  );
};

export default Profile;
