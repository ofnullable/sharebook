import React, { useState } from 'react';
import { useDispatch } from 'react-redux';

import { useInput, hasWhitespace } from '@utils/inputUtils';
import { updateInfoRequest } from '@redux/actions/userActions';

import { ProfileModifyFormWrapper, PasswordSelector } from './ProfileModifyForm.styled';
import { Button, InputGroup } from '@styles/common';

const ProfileModifyForm = ({ data }) => {
  const [name, nameHandler] = useInput(data.name);
  const [newPassword, setNewPassword] = useState('');
  const [newPasswordCheck, setNewPasswordCheck] = useState('');
  const dispatch = useDispatch();

  const handlePasswordSelectorClick = e => {
    e.target.classList.toggle('active');
  };

  const handleNewPasswordChange = e => {
    setNewPassword(e.target.value.trim());
  };

  const handleNewPasswordCheckChange = e => {
    setNewPasswordCheck(e.target.value.trim());
  };

  const handleProfileModify = e => {
    e.preventDefault();
    if (!name) {
      alert('?');
      return;
    }
    if (hasWhitespace(newPassword)) {
      alert('비밀번호는 공백문자를 포함할 수 없습니다.');
      return;
    }
    if (newPassword !== newPasswordCheck) {
      alert('?');
      return;
    }

    dispatch(updateInfoRequest({ id: data.id, name, newPassword }));
  };

  return (
    <ProfileModifyFormWrapper onSubmit={handleProfileModify}>
      <InputGroup>
        <label htmlFor='email'>이메일</label>
        <input id='email' type='text' value={data.email} readOnly />
      </InputGroup>
      <InputGroup>
        <label htmlFor='name'>이름</label>
        <input id='name' type='text' value={name} onChange={nameHandler} />
      </InputGroup>

      <PasswordSelector onClick={handlePasswordSelectorClick}>비밀번호 변경하기</PasswordSelector>

      <div>
        <InputGroup>
          <label htmlFor='newPassword'>새 비밀번호</label>
          <input
            id='newPassword'
            type='password'
            value={newPassword}
            onChange={handleNewPasswordChange}
          />
        </InputGroup>
        <InputGroup>
          <label htmlFor='newPasswordCheck'>새 비밀번호 확인</label>
          <input
            id='newPasswordCheck'
            type='password'
            value={newPasswordCheck}
            onChange={handleNewPasswordCheckChange}
          />
        </InputGroup>
      </div>

      <Button _color='primary'>변경하기</Button>
    </ProfileModifyFormWrapper>
  );
};

export default ProfileModifyForm;
