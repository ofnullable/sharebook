import React from 'react';

import { Alert } from './ActionResultAlert.styled';

const ActionResultAlert = ({ isSuccess, close, link }) => {
  const handleClose = () => {
    close();
  };

  return isSuccess ? (
    <Alert _color='primary'>
      <span>요청성공!</span>
      {link && (
        <Link {...link}>
          <a>
            <i className='material-icons-outlined'>arrow_right</i>
          </a>
        </Link>
      )}
      <i className='material-icons-outlined' onClick={handleClose}>
        clear
      </i>
    </Alert>
  ) : (
    <Alert _color='red'>
      <span>요청실패..</span>
      <i className='material-icons-outlined' onClick={handleClose}>
        clear
      </i>
    </Alert>
  );
};

export default ActionResultAlert;
