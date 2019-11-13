import React from 'react';
import { useDispatch } from 'react-redux';

import {
  acceptLendingRequest,
  rejectLendingRequest,
  returnBookRequest,
} from '@redux/actions/lendingActions';

import { ManagementButtonWrapper } from './ManagementButton.styled';
import { Button } from '@styles/common';

const ManagementButton = ({ status, lendingId }) => {
  const dispatch = useDispatch();

  const handleAccept = () => {
    dispatch(acceptLendingRequest(lendingId, 'requests'));
  };
  const handleReject = () => {
    dispatch(rejectLendingRequest(lendingId, 'requests'));
  };
  const handleReturn = () => {
    dispatch(returnBookRequest(lendingId, 'requests'));
  };

  return status === 'requested' ? (
    <ManagementButtonWrapper>
      <Button className='reject' onClick={handleReject}>
        거절
      </Button>
      <Button _color='primary' onClick={handleAccept}>
        수락
      </Button>
    </ManagementButtonWrapper>
  ) : (
    <ManagementButtonWrapper>
      <Button _color='secondary' className='return' onClick={handleReturn}>
        회수
      </Button>
    </ManagementButtonWrapper>
  );
};

export default ManagementButton;
