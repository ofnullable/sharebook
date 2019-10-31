import React from 'react';
import Link from 'next/link';
import { useDispatch, useSelector } from 'react-redux';

import { BOOK_STATUS, LENDING_STATUS } from '@utils/consts';
import {
  borrowBookRequest,
  cancelBorrowRequest,
  acceptLendingRequest,
  rejectLendingRequest,
  returnBookRequest,
} from '@redux/actions/lendingActions';

import { Button } from '@styles/common';

const LendingButton = ({ detail }) => {
  const lending = useSelector(state => state.lending.latestLending.data);
  const user = useSelector(state => state.user.user.data);
  const dispatch = useDispatch();

  const handleBorrow = () => {
    dispatch(borrowBookRequest(lending.book.id));
  };

  const handleAccept = () => {
    dispatch(acceptLendingRequest(lending.id));
  };

  const handleReject = () => {
    dispatch(rejectLendingRequest(lending.id));
  };

  const handleCancel = () => {
    dispatch(cancelBorrowRequest(lending.id));
  };

  const handleReturn = () => {
    dispatch(returnBookRequest(lending.id));
  };

  // 로그인하지 않은 경우
  if (!user.id) {
    return (
      <Link href={{ pathname: '/signin' }} prefetch={false}>
        <Button>로그인하기</Button>
      </Link>
    );
  }

  // 도서의 주인이 현재 로그인한 사용자인 경우
  if (detail.owner === user.name) {
    if (detail.status === BOOK_STATUS.UNAVAILABLE) {
      if (lending.currentStatus === LENDING_STATUS.REQUESTED) {
        return (
            <>
              <Button _color='primary' onClick={handleAccept}>수락</Button>
              <Button style={{ color: 'red' }} onClick={handleReject}>거절</Button>
            </>
        );
      } else {
        return <Button onClick={handleReturn}>반납처리</Button>;
      }
    }
    return (
        <Button>대여기록확인</Button>
    );
  }

  // 현재 도서가 대여 가능한 도서인 경우
  if (detail.status === BOOK_STATUS.AVAILABLE) {
    return (
      <Button _color='primary' onClick={handleBorrow}>
        대여신청
      </Button>
    );
  }

  // 현재 대여 (또는 대여 요청) 중인 회원이 현재 로그인한 회원인 경우
  if (lending.borrowerId === user.id) {
    if (lending.currentStatus === LENDING_STATUS.REQUESTED) {
      return (
          <Button _color='red' onClick={handleCancel}>신청취소</Button>
      )
    } else if (lending.currentStatus === LENDING_STATUS.ACCEPTED) {
      return (
          <Button _color='secondary' onClick={handleReturn}>반납하기</Button>
      )
    }
  }

  // 대여할 수 없는 경우
  return (
    <Button _color='gray' disabled>
      대여불가
    </Button>
  );
};

export default LendingButton;
