import React from 'react';
import { useDispatch } from 'react-redux';

import { useInput } from '../components/utils/Input';
import { signInRequest } from '../redux/actions/userActions';

import { CenteredDiv, InputGroup, Button } from '../styles/global';
import { SignInForm } from '../styles/pages/signin.styled';

function SignIn() {
  const [username, usernameHandler] = useInput();
  const [password, passwordHandler] = useInput();
  const dispatch = useDispatch();

  const handleSubmit = e => {
    e.preventDefault();

    dispatch(signInRequest({ username, password }));
  };

  return (
    <CenteredDiv>
      <h1>Sign in to PublicShare</h1>
      <SignInForm onSubmit={handleSubmit}>
        <InputGroup>
          <label htmlFor='username'>Username</label>
          <input
            required
            id='username'
            type='text'
            value={username}
            onChange={usernameHandler}
            placeholder='your@email.com'
          />
        </InputGroup>
        <InputGroup>
          <label htmlFor='password'>Password</label>
          <input
            required
            id='password'
            type='password'
            value={password}
            onChange={passwordHandler}
          />
        </InputGroup>
        <Button _color='primary' type='submit'>
          Sign in
        </Button>
      </SignInForm>
    </CenteredDiv>
  );
}

export default SignIn;
