import React from 'react';
import Link from 'next/link';
import moment from 'moment';
import { useDispatch } from 'react-redux';

import { cancelBorrowRequest, returnBookRequest } from '@redux/actions/lendingActions';

import {
  LendingHistoryWrapper,
  ThumbnailImage,
  LendingInfoWrapper,
  ButtonWrapper,
} from './LendingsHistory.styled';
import { Button } from '@styles/common';

moment.locale('ko');

const LendingHistory = ({ data }) => {
  const dispatch = useDispatch();

  const handleCancel = () => {
    dispatch(cancelBorrowRequest(data.id, 'myRequests'));
  };
  const handleReturn = () => {
    dispatch(returnBookRequest(data.id, 'myRequests'));
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
    <LendingHistoryWrapper>
      <Link href='/book/[id]' as={`/book/${data.book.id}`}>
        <a>
          <ThumbnailImage src={data.book.imageUrl} alt={`이미지 - ${data.book.title}`} />
        </a>
      </Link>
      <LendingInfoWrapper>
        <span>{data.book.category}</span>
        <p>{data.book.title}</p>
        <p>{data.endedAt && moment(data.endedAt).from(data.startedAt)}</p>
        <span>{data.startedAt && moment(data.startedAt).format('YYYY.MM.DD hh:mm')}</span>
      </LendingInfoWrapper>
      <ButtonWrapper>{ChangeStatusButton()}</ButtonWrapper>
    </LendingHistoryWrapper>
  );
};

export default LendingHistory;
