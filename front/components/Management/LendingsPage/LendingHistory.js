import React from 'react';
import { useDispatch } from 'react-redux';

import { cancelBorrowRequest, returnBookRequest } from '@redux/actions/lendingActions';

import {
  ItemWrapper,
  ThumbnailImage,
  LendingInfoWrapper,
  ButtonWrapper,
} from './LendingsHistory.styled';
import { Button } from '@styles/common';

const LendingHistory = ({ data }) => {
  const dispatch = useDispatch();

  const handleCancel = () => {
    dispatch(cancelBorrowRequest(data.id));
  };
  const handleReturn = () => {
    dispatch(returnBookRequest(data.id));
  };

  const ChangeStatusButton = () => {
    if (data.currentStatus === 'REQUESTED') {
      return (
        <Button _color='red' onClick={handleCancel}>
          취소
        </Button>
      );
    }
    if (data.currentStatus === 'ACCEPTED') {
      return (
        <Button _color='secondary' onClick={handleReturn}>
          반납
        </Button>
      );
    }
    return null;
  };
  return (
    <ItemWrapper>
      <ThumbnailImage src={data.book.imageUrl} alt={`이미지 - ${data.book.title}`} />
      <LendingInfoWrapper>
        <span>{data.book.category}</span>
        <p>{data.book.title}</p>
        <span style={{ fontSize: '90%' }}>{data.startedAt}</span>
      </LendingInfoWrapper>
      <ButtonWrapper>{ChangeStatusButton()}</ButtonWrapper>
    </ItemWrapper>
  );
};

export default LendingHistory;
