import React from 'react';

import { ScreenOverlay, LoadingIconWrapper, LoadingIcon } from '@styles/common';

const LoadingOverlay = () => {
  return (
    <>
      <ScreenOverlay />
      <LoadingIconWrapper>
        <LoadingIcon _size='100px' _color='primary' className='material-icons-outlined'>
          autorenew
        </LoadingIcon>
      </LoadingIconWrapper>
    </>
  );
};

export default LoadingOverlay;
