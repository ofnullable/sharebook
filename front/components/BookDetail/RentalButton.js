import React from 'react';
import { useDispatch } from 'react-redux';

import { BOOK_STATUS } from '@utils/consts';
import { rentalBookRequest } from '@redux/actions/rentalActions';

import { Button } from '@styles/common';

function RentalButton({ detail, histories }) {
  const dispatch = useDispatch();

  const handleRent = () => {
    dispatch(rentalBookRequest(detail.id));
  };

  if (detail.status === BOOK_STATUS.AVAILABLE) {
    return (
      <Button _color='primary' onClick={handleRent}>
        대여신청
      </Button>
    );
  }

  const wasRent = histories.filter(h => h.bookId === detail.id).length;

  return wasRent ? (
    <Button _color='gray' disabled>
      대여중
    </Button>
  ) : (
    <Button _color='gray' disabled>
      대여불가
    </Button>
  );
}

export default RentalButton;
