import React, { useState } from 'react';
import { SignInForm, SignInFormWrapper } from '../styles/signin.styled';
import { Button } from '../styles/global';

function SignIn() {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');

  const handleSubmit = e => {
    e.preventDefault();
  };

  const handleUsernameChange = e => {
    setUsername(e.target.value);
  };

  const handlePasswordChange = e => {
    setPassword(e.target.value);
  };

  return (
    <SignInFormWrapper>
      <h1>Share your books!</h1>
      <SignInForm onSubmit={handleSubmit}>
        <label htmlFor='username'>username</label>
        <input id='username' type='text' value={username} onChange={handleUsernameChange} />
        <label htmlFor='password'>password</label>
        <input id='password' type='password' value={password} onChange={handlePasswordChange} />
        <Button _color='primary' type='submit'>
          Sign In
        </Button>
      </SignInForm>
    </SignInFormWrapper>
  );
}

export default SignIn;
