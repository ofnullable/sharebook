import React from 'react';
import { useSelector } from 'react-redux';

import { SubMenu } from './index.styled';

const LendingList = () => {
  return (
    <>
      <SubMenu>
        <span>요청한도서</span>
        <span>대여중도서</span>
      </SubMenu>
    </>
  );
};

export default LendingList;
