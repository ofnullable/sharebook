import React, { useEffect } from 'react';
import Link from 'next/link';
import { useRouter } from 'next/router';
import { useDispatch, useSelector } from 'react-redux';

import { useInput } from '@utils/inputUtils';
import { signInRequest } from '@redux/actions/userActions';

import { CenterDiv, InputGroup, Button, LoadingIcon, CenterForm, ButtonLink } from '@styles/common';

const SignInPage = () => {
  const { isSignedIn, isLoading, signInError, error } = useSelector(state => state.user.user);
  const [username, usernameHandler, setUsername] = useInput();
  const [password, passwordHandler] = useInput();
  const dispatch = useDispatch();
  const router = useRouter();

  useEffect(() => {
    if (isSignedIn) {
      const referrer = document.referrer;
      const origin = location.origin;

      if (referrer.startsWith(origin) && !referrer.endsWith('/join')) {
        router.back();
      } else {
        router.push('/');
      }
    }
  }, [isSignedIn]);

  const handleSubmit = e => {
    e.preventDefault();
    setUsername(username.trim().toLowerCase());
    dispatch(signInRequest({ username, password }));
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
        {signInError && <p style={{ color: 'red', textAlign: 'center' }}>{error.message}</p>}
        <CenterDiv>
          {isLoading ? (
            <Button _color='primary' type='submit' disabled>
              <LoadingIcon _size='14px' className='material-icons'>
                autorenew
              </LoadingIcon>
            </Button>
          ) : (
            <Button _color='primary' type='submit'>
              로그인
            </Button>
          )}
        </CenterDiv>
        <CenterDiv>
          <Link href='/join'>
            <ButtonLink>아직 회원이 아니신가요?</ButtonLink>
          </Link>
        </CenterDiv>
      </CenterForm>
    </CenterDiv>
  );
};

export default SignInPage;
