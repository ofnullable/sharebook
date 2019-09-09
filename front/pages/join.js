import React, { useState } from 'react';
import { useSelector } from 'react-redux';

import { useInput } from '@components/utils/InputUtils';

import { Button, CenterDiv, InputGroup, CenterForm } from '@styles/global';

function Join() {
  const [username, usernameHandler] = useInput();
  const [name, nameHandler] = useInput();
  const [password, setPassword] = useState('');
  const [passwordCheck, setPasswordCheck] = useState('');

  const { isLoading } = useSelector(state => state.user);

  const handleSubmit = e => {
    e.preventDefault();
  };

  const handlePasswordChange = e => {
    setPassword(e.target.value);
  };

  const handlePasswordCheckChange = e => {
    setPasswordCheck(e.target.value);
  };

  return (
    <CenterDiv>
      <h1 className='title'>회원가입</h1>
      <CenterForm onSubmit={handleSubmit}>
        <InputGroup>
          <label htmlFor='username'>
            이메일<span className='import'>*</span>
          </label>
          <input
            required
            id='username'
            type='text'
            placeholder='your@email.com'
            value={username}
            onChange={usernameHandler}
          />
        </InputGroup>
        <InputGroup>
          <label htmlFor='name'>
            이름<span className='import'>*</span>
          </label>
          <input
            required
            id='name'
            type='text'
            placeholder='홍길동'
            value={name}
            onChange={nameHandler}
          />
        </InputGroup>
        <InputGroup>
          <label htmlFor='password'>
            비밀번호<span className='import'>*</span>
          </label>
          <input
            required
            id='password'
            type='password'
            value={password}
            onChange={handlePasswordChange}
          />
        </InputGroup>
        <InputGroup>
          <label htmlFor='passwordCheck'>
            비밀번호 확인<span className='import'>*</span>
          </label>
          <input
            required
            id='passwordCheck'
            type='password'
            value={passwordCheck}
            onChange={handlePasswordCheckChange}
          />
        </InputGroup>
        <CenterDiv>
          <Button _color='primary' type='submit'>
            {isLoading ? (
              <SpinIcon _size='14px' className='material-icons'>
                autorenew
              </SpinIcon>
            ) : (
              '회원가입'
            )}
          </Button>
        </CenterDiv>
      </CenterForm>
    </CenterDiv>
  );
}

export default Join;
