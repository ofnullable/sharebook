import React from 'react';
import { useSelector } from 'react-redux';

import { Title } from './index.styled';

const RentalList = () => {
  const histories = useSelector(state => state.rental.histories.data);
  return (
    <div>
      <Title>대여목록</Title>
    </div>
  );
};

export default RentalList;
