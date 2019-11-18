import React, { useState, useEffect } from 'react';
import Link from 'next/link';
import Router from 'next/router';
import { useDispatch, useSelector } from 'react-redux';

import LoadingOverlay from '@components/common/LoadingOverlay';
import { useInput, isEmail } from '@utils/inputUtils';
import { signUpRequest, emailDuplicationCheckRequest } from '@redux/actions/userActions';

import { SuccessText, ErrorText } from './index.styled';
import { Button, LoadingIcon, CenterDiv, InputGroup, CenterForm, ButtonLink } from '@styles/common';

const JoinPage = () => {
  const [email, setEmail] = useState('');
  const [emailChecked, setEmailChecked] = useState(false);
  const [name, nameHandler] = useInput();
  const [password, setPassword] = useState('');
  const [passwordCheck, setPasswordCheck] = useState('');
  const [passwordError, setPasswordError] = useState(false);

  const { isSignedIn } = useSelector(state => state.user.user);
  const { error, isLoading, isDuplicated } = useSelector(state => state.user.join);
  const dispatch = useDispatch();

  useEffect(() => {
    if (isSignedIn) {
      const referrer = document.referrer;
      const origin = location.origin;

      if (referrer.startsWith(origin) && !referrer.endsWith('/signin')) {
        Router.back();
      } else {
        Router.push('/');
      }
    }
  }, [isSignedIn]);

  const handleSubmit = e => {
    e.preventDefault();

    if (!emailChecked || isDuplicated) {
      return;
    }

    if (password !== passwordCheck) {
      setPasswordError(true);
      return;
    }

    dispatch(signUpRequest({ email: { address: email.trim().toLowerCase() }, name, password }));
  };

  const handleEmailChange = e => {
    if (emailChecked) {
      setEmailChecked(false);
    }
    setEmail(e.target.value);
  };

  const handlePasswordChange = e => {
    setPassword(e.target.value);
  };

  const handlePasswordCheckChange = e => {
    setPasswordCheck(e.target.value);
  };

  const emailDuplicateCheck = () => {
    if (isEmail(email) && (!emailChecked || isDuplicated)) {
      dispatch(emailDuplicationCheckRequest(email));
      setEmailChecked(true);
    }
  };

  const getEmailError = () => {
    if (error.status === 409) {
      return <ErrorText>이미 사용중인 아이디 입니다.</ErrorText>;
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
      {isLoading && <LoadingOverlay />}
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
            pattern='^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,6}$'
            title='이메일 형식이 올바르지 않습니다.'
            value={email}
            onChange={handleEmailChange}
            onBlur={emailDuplicateCheck}
          />
        </InputGroup>
        {emailChecked && !isDuplicated && (
          <SuccessText>사용 가능한 아이디(이메일) 입니다.</SuccessText>
        )}
        {emailChecked && isDuplicated && (
          <ErrorText>이미 사용중인 아이디(이메일) 입니다.</ErrorText>
        )}
        {error.status && getEmailError()}
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
          <Link href='/signin'>
            <ButtonLink>이미 계정이 있으신가요?</ButtonLink>
          </Link>
        </CenterDiv>
      </CenterForm>
    </CenterDiv>
  );
};

export default JoinPage;
