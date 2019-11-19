import styled from 'styled-components';

import device from '@styles/device';

export const PasswordVerifyForm = styled.form`
  height: 400px;
  padding-top: 150px;
  text-align: center;

  & p {
    font-weight: initial;
  }
  & input {
    ${device.laptops`
      width: 60%;
    `};
    ${device.tablets`
      width: 70%;
    `};
    ${device.mobiles`
      width: 100%;
    `};
  }
`;
