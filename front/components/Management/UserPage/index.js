import React from 'react';
import { useSelector, useDispatch } from 'react-redux';

import EditProfileForm from './EditProfileForm';
import ReviewManagement from './ReviewManagement';
import LeftMenu from '@components/Management/LeftMenu';
import SubMenu from '@components/Management/SubMenu';
import { useInput } from '@utils';
import { signOutRequest, passwordVerifyRequest } from '@redux/actions/userActions';

import { PasswordVerifyForm } from './index.styled';
import { WithLeftMenu, Title } from '@components/Management/styled';
import { Button, CenterDiv, InputGroup } from '@styles/common';

const SUBMENU = {
  profile: '프로필관리',
  reviews: '리뷰관리',
};

const UserPage = ({ menu }) => {
  const [password, passwordHandler] = useInput();
  const { verified } = useSelector(state => state.user.user.data);
  const dispatch = useDispatch();

  const handleSignOut = () => {
    dispatch(signOutRequest());
  };

  const handlePasswordVerifySubmit = e => {
    e.preventDefault();
    if (password) {
      dispatch(passwordVerifyRequest(password));
    }
  };

  const renderContents = () => {
    if (menu === 'profile') {
      if (!verified) {
        return (
          <PasswordVerifyForm onSubmit={handlePasswordVerifySubmit}>
            <div>
              <p>개인정보 보호를 위해 비밀번호를 다시 입력해주세요.</p>
              <InputGroup>
                <input type='password' value={password} onChange={passwordHandler} required />
              </InputGroup>
              <Button _color='primary'>확인</Button>
            </div>
          </PasswordVerifyForm>
        );
      }
      return <EditProfileForm />;
    }

    return <ReviewManagement />;
  };

  return (
    <CenterDiv>
      <LeftMenu menu={'profile'} />

      <WithLeftMenu>
        <Title>개인관리</Title>
        <Button _color='gray' onClick={handleSignOut}>
          로그아웃
        </Button>
        <SubMenu href='/management/user/[menu]' currentMenu={menu} menus={SUBMENU} />
        {renderContents()}
      </WithLeftMenu>
    </CenterDiv>
  );
};

export default UserPage;
