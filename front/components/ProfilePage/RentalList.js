import React from 'react';
import { useSelector } from 'react-redux';

const RentalList = () => {
  const histories = useSelector(state => state.rental.histories.data);
  return (
    <div>
      <h1>대여목록</h1>
    </div>
  );
};

export default RentalList;
