import React, { useEffect } from 'react';
import Link from 'next/link';
import Router from 'next/router';
import { useDispatch, useSelector } from 'react-redux';

import { useInput } from '@utils/InputUtils';
import { signInRequest } from '@redux/actions/userActions';

import { CenterDiv, InputGroup, Button, SpinIcon, CenterForm, ButtonLink } from '@styles/common';

const SignInPage = () => {
  const { isSignedIn, isLoading } = useSelector(state => state.user.user);
  const { data } = useSelector(state => state.user.join);
  const [username, usernameHandler] = useInput(data && data.email);
  const [password, passwordHandler] = useInput();
  const dispatch = useDispatch();

  useEffect(() => {
    if (isSignedIn) {
      const referrer = document.referrer;
      const origin = document.location.origin;

      if (referrer.startsWith(origin)) {
        Router.back();
      } else {
        Router.push('/');
      }
    }
  }, [isSignedIn]);

  const handleSubmit = e => {
    e.preventDefault();
    dispatch(signInRequest({ username: username.trim().toLowerCase(), password }));
  };

  return (
    <CenterDiv>
      <h1 className='title'>로그인</h1>
      <CenterForm onSubmit={handleSubmit}>
        <InputGroup>
          <label htmlFor='username'>이메일</label>
          <input
            required
            id='username'
            type='email'
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
        <CenterDiv>
          {isLoading ? (
            <Button _color='primary' type='submit' disabled>
              <SpinIcon _size='14px' className='material-icons'>
                autorenew
              </SpinIcon>
            </Button>
          ) : (
            <Button _color='primary' type='submit'>
              로그인
            </Button>
          )}
        </CenterDiv>
        <CenterDiv>
          <Link href='/join' prefetch={false}>
            <ButtonLink>아직 회원이 아니신가요?</ButtonLink>
          </Link>
        </CenterDiv>
      </CenterForm>
    </CenterDiv>
  );
};

export default SignInPage;
