import React, { useEffect } from 'react';
import Link from 'next/link';
import Router from 'next/router';
import { useDispatch, useSelector } from 'react-redux';

import { useInput } from '@components/utils/Input';
import { signInRequest } from '@redux/actions/userActions';

import {
  CenterAlignDiv,
  InputGroup,
  Button,
  SpinIcon,
  CenterForm,
  ButtonLink,
} from '@styles/global';

function SignIn() {
  const [username, usernameHandler] = useInput();
  const [password, passwordHandler] = useInput();
  const { isSignedIn, isLoading } = useSelector(state => state.user);
  const dispatch = useDispatch();

  const handleSubmit = e => {
    e.preventDefault();
    dispatch(signInRequest({ username, password }));
  };

  useEffect(() => {
    if (isSignedIn) {
      Router.push('/');
    }
  }, [isSignedIn]);

  return (
    <CenterAlignDiv>
      <h1>로그인</h1>
      <CenterForm onSubmit={handleSubmit}>
        <InputGroup>
          <label htmlFor='username'>이메일</label>
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
          <label htmlFor='password'>비밀번호</label>
          <input
            required
            id='password'
            type='password'
            value={password}
            onChange={passwordHandler}
          />
        </InputGroup>
        <CenterAlignDiv>
          <Button _color='primary' type='submit'>
            {isLoading ? (
              <SpinIcon _size='14px' className='material-icons'>
                autorenew
              </SpinIcon>
            ) : (
              '로그인'
            )}
          </Button>
        </CenterAlignDiv>
        <CenterAlignDiv>
          <Link href='/join' prefetch={false}>
            <ButtonLink>아직 회원이 아니신가요?</ButtonLink>
          </Link>
        </CenterAlignDiv>
      </CenterForm>
    </CenterAlignDiv>
  );
}

export default SignIn;
