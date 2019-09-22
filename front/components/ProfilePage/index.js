import React, { useState, useEffect } from 'react';
import { useSelector } from 'react-redux';
import { useRouter } from 'next/router';

import Profile from './Profile';
import ManageBook from './ManageBook';
import RentalList from './RentalList';

import { LeftMenu, MenuItem, WithLeftMenu } from './index.styled';
import { CenterDiv } from '@styles/common';

const menu = {
  profile: { name: '프로필', component: <Profile /> },
  manageBook: { name: '도서관리', component: <ManageBook /> },
  rentalList: { name: '대여목록', component: <RentalList /> },
};
const activeStyle = { backgroundColor: '#e9ecef' };

const ProfilePage = () => {
  const [active, setActive] = useState('profile');
  const { isSignedIn, isLoading } = useSelector(state => state.user.user);
  const router = useRouter();

  useEffect(() => {
    if (!isLoading && !isSignedIn) {
      router.push('/signin');
    }
  }, [isLoading, isSignedIn]);

  const handleMenuClick = e => {
    setActive(e.target.id);
  };

  return (
    <CenterDiv>
      <LeftMenu>
        <h2>My page</h2>
        <ul>
          {Object.keys(menu).map((m, i) => {
            return (
              <MenuItem
                key={i}
                id={m}
                onClick={handleMenuClick}
                style={active === m ? activeStyle : {}}
              >
                {menu[m].name}
              </MenuItem>
            );
          })}
        </ul>
      </LeftMenu>
      <WithLeftMenu>{menu[active].component}</WithLeftMenu>
    </CenterDiv>
  );
};

export default ProfilePage;
