import React, { useState } from 'react';
import { SignUpForm } from '../styles/pages/join.styled';
import { Button, CenteredDiv, InputGroup } from '../styles/global';
import { useInput } from '../components/utils/Input';

function Join() {
  const [username, usernameHandler] = useInput();
  const [name, nameHandler] = useInput();
  const [password, setPassword] = useState('');
  const [passwordCheck, setPasswordCheck] = useState('');

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
    <CenteredDiv>
      <h1>Join - Share your books!</h1>
      <SignUpForm onSubmit={handleSubmit}>
        <InputGroup>
          <label htmlFor='username'>
            Username <span>*</span>
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
            Name <span>*</span>
          </label>
          <input
            required
            id='name'
            type='text'
            placeholder='name'
            value={name}
            onChange={nameHandler}
          />
        </InputGroup>
        <InputGroup>
          <label htmlFor='password'>
            Password <span>*</span>
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
            Password Check <span>*</span>
          </label>
          <input
            required
            id='passwordCheck'
            type='password'
            value={passwordCheck}
            onChange={handlePasswordCheckChange}
          />
        </InputGroup>
        <Button _color='primary' type='submit'>
          Sign In
        </Button>
      </SignUpForm>
    </CenteredDiv>
  );
}

export default Join;
