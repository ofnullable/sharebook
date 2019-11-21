import React from 'react';
import { useDispatch } from 'react-redux';

import { cancelBorrowRequest, returnBookRequest } from '@redux/actions/lendingActions';

import { Button } from '@styles/common';

const LendingButton = ({ currentStatus }) => {
  const dispatch = useDispatch();

  const handleCancel = () => {
    dispatch(cancelBorrowRequest(data.id, 'myRequests'));
  };
  const handleReturn = () => {
    dispatch(returnBookRequest(data.id, 'myRequests'));
  };

  if (currentStatus === 'REQUESTED') {
    return (
      <Button _color='red' onClick={handleCancel}>
        취소
      </Button>
    );
  }
  if (currentStatus === 'ACCEPTED') {
    return (
      <Button _color='secondary' onClick={handleReturn}>
        반납
      </Button>
    );
  }
  return null;
};

export default LendingButton;
