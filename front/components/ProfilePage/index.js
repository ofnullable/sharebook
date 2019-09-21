import React, { useState, useEffect } from 'react';
import { useSelector } from 'react-redux';
import { useRouter } from 'next/router';

import { LeftMenu, MenuItem, WithLeftMenu } from './index.styled';
import { CenterDiv } from '@styles/common';

const menu = {
  profile: { name: '프로필', component: '' },
  manageBook: { name: '도서관리', component: '' },
  rentalList: { name: '대여목록', component: '' },
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
        <ul onClick={handleMenuClick}>
          {Object.keys(menu).map((m, i) => {
            return (
              <MenuItem key={i} style={active === m ? activeStyle : {}} id={m}>
                {menu[m].name}
              </MenuItem>
            );
          })}
        </ul>
      </LeftMenu>
      <WithLeftMenu>등록한 도서 목록</WithLeftMenu>
    </CenterDiv>
  );
};

export default ProfilePage;
