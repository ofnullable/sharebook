import React, { useState, useEffect } from 'react';
import Link from 'next/link';
import Router from 'next/router';
import { useDispatch, useSelector } from 'react-redux';

import { useInput } from '@utils/inputUtils';
import { signUpRequest } from '@redux/actions/userActions';

import { ErrorText } from './index.styled';
import { Button, LoadingIcon, CenterDiv, InputGroup, CenterForm, ButtonLink } from '@styles/common';

const JoinPage = () => {
  const [email, emailHandler] = useInput();
  const [name, nameHandler] = useInput();
  const [password, setPassword] = useState('');
  const [passwordCheck, setPasswordCheck] = useState('');
  const [passwordError, setPasswordError] = useState(false);

  const { isSignedIn } = useSelector(state => state.user.user);
  const { data, error, isLoading } = useSelector(state => state.user.join);
  const dispatch = useDispatch();

  useEffect(() => {
    if (isSignedIn) {
      Router.back();
    }
  }, [isSignedIn]);

  useEffect(() => {
    if (data.email) {
      Router.replace('/signin');
    }
  }, [data]);

  const handleSubmit = e => {
    e.preventDefault();

    if (password !== passwordCheck) {
      setPasswordError(true);
      return;
    }

    dispatch(signUpRequest({ email: { address: email.trim().toLowerCase() }, name, password }));
  };

  const handlePasswordChange = e => {
    setPassword(e.target.value);
  };

  const handlePasswordCheckChange = e => {
    setPasswordCheck(e.target.value);
  };

  const getEmailError = () => {
    if (error.status === 409) {
      return '이미 사용중인 아이디 입니다.';
    }
    if (error.errors && error.errors['email.address']) {
      return <ErrorText>{error.errors['email.address']}</ErrorText>;
    }
  };

  const getPasswordError = () => {
    if (error.errors && error.errors.password) {
      return <ErrorText>{error.errors.password}</ErrorText>;
    }
  };

  return (
    <CenterDiv>
      <h1 className='title'>회원가입</h1>
      <CenterForm onSubmit={handleSubmit}>
        <InputGroup>
          <label htmlFor='email'>
            이메일<span className='import'>*</span>
          </label>
          <input
            required
            id='email'
            type='email'
            placeholder='your@email.com'
            value={email}
            onChange={emailHandler}
          />
        </InputGroup>
        {getEmailError()}
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
            minLength='6'
            onChange={handlePasswordChange}
          />
        </InputGroup>
        {error.errors && getPasswordError()}
        <InputGroup>
          <label htmlFor='passwordCheck'>
            비밀번호 확인<span className='import'>*</span>
          </label>
          <input
            required
            id='passwordCheck'
            type='password'
            value={passwordCheck}
            minLength='6'
            onChange={handlePasswordCheckChange}
          />
        </InputGroup>
        {passwordError && <ErrorText>비밀번호가 일치하지 않습니다</ErrorText>}
        <CenterDiv>
          {isLoading ? (
            <Button _color='primary' type='submit' disabled>
              <LoadingIcon _size='14px' className='material-icons'>
                autorenew
              </LoadingIcon>
            </Button>
          ) : (
            <Button _color='primary' type='submit'>
              회원가입
            </Button>
          )}
        </CenterDiv>
        <CenterDiv>
          <Link href='/signin' prefetch={false}>
            <ButtonLink>이미 계정이 있으신가요?</ButtonLink>
          </Link>
        </CenterDiv>
      </CenterForm>
    </CenterDiv>
  );
};

export default JoinPage;
