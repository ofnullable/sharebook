import React from 'react';
import Link from 'next/link';
import { useDispatch } from 'react-redux';

import { closeAlert } from '@redux/actions/registerActions';

import { Alert } from './RegisterResultAlert.styled';

const RegisterResultAlert = ({ id }) => {
  const dispatch = useDispatch();

  const handleClose = () => {
    dispatch(closeAlert());
  };

  return id ? (
    <Alert _color='primary'>
      <span>등록성공!</span>
      <Link href='/book/[id]' as={`/book/${id}`}>
        <a>
          <i className='material-icons-outlined'>arrow_right</i>
        </a>
      </Link>
      <i className='material-icons-outlined' onClick={handleClose}>
        clear
      </i>
    </Alert>
  ) : (
    <Alert _color='red'>
      <span>등록실패..</span>
      <i className='material-icons-outlined' onClick={handleClose}>
        clear
      </i>
    </Alert>
  );
};

export default RegisterResultAlert;
