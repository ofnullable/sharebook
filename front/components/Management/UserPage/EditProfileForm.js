import React, { useState, useEffect, useCallback } from 'react';
import { useDispatch, useSelector } from 'react-redux';

import ActionResultAlert from '@components/common/ActionResultAlert';
import ImageUploader from '@components/common/ImageUploader';
import { useInput, hasWhitespace, getAvatar, getGravatar } from '@utils';
import { updateInfoRequest, updateAvatarRequest } from '@redux/actions/userActions';

import {
  EditProfileFormWrapper,
  ProfileImage,
  ChangePasswordButton,
} from './EditProfileForm.styled';
import { Button, InputGroup } from '@styles/common';

const EditProfileForm = () => {
  const user = useSelector(state => state.user.user);

  const [name, nameHandler] = useInput(user.data.name);
  const [newPassword, setNewPassword] = useState('');
  const [newPasswordCheck, setNewPasswordCheck] = useState('');
  const [updated, setUpdated] = useState(false);
  const [showAlert, setShowAlert] = useState(false);

  const dispatch = useDispatch();

  useEffect(() => {
    if (updated) {
      setShowAlert(true);
    }
  }, [user.data, updated]);

  const handleChangePassword = e => {
    e.target.classList.toggle('active');
  };

  const handleNewPasswordChange = e => {
    setNewPassword(e.target.value);
  };

  const handleNewPasswordCheckChange = e => {
    setNewPasswordCheck(e.target.value);
  };

  const handleEditProfile = e => {
    e.preventDefault();
    if (!name.trim()) {
      alert('이름은 공백일 수 없습니다.');
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

    dispatch(updateInfoRequest({ id: user.data.id, name, newPassword }));
    setUpdated(true);
  };

  const handleAvatarUpload = useCallback(file => {
    const formData = new FormData();
    formData.append('avatar', file);

    dispatch(updateAvatarRequest(formData));
    setUpdated(true);
  }, []);

  const closeAlert = useCallback(() => {
    setShowAlert(false);
    setUpdated(false);
  }, []);

  return (
    <EditProfileFormWrapper onSubmit={handleEditProfile}>
      {showAlert && (
        <ActionResultAlert isSuccess={!Object.keys(user.error).length} close={closeAlert} />
      )}
      <ImageUploader
        StyledTag={ProfileImage}
        defaultImage={getAvatar(user.data.avatar) || getGravatar(user.data.email, 200)}
        handleUpload={handleAvatarUpload}
      />
      <InputGroup>
        <label htmlFor='email'>이메일</label>
        <input id='email' type='text' value={user.data.email} readOnly />
      </InputGroup>
      <InputGroup>
        <label htmlFor='name'>이름</label>
        <input id='name' type='text' value={name} onChange={nameHandler} />
      </InputGroup>

      <ChangePasswordButton onClick={handleChangePassword}>비밀번호 변경하기</ChangePasswordButton>
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

      <Button _color='primary'>변경사항 저장</Button>
    </EditProfileFormWrapper>
  );
};

export default EditProfileForm;
