import styled from 'styled-components';

import device from '@styles/device';
import { transition } from '@styles/mixins';
import { COLOR_SCHEME } from '@styles/colors';

export const ProfileModifyFormWrapper = styled.form`
  ${device.laptops`
    width: 70%;
    margin: 1em 15%;
  `};
  ${device.tablets`
    width: 80%;
    margin: 1em 10%;
  `};
  ${device.mobiles`
    width: 100%;
    margin: 1em auto;
  `};
  ${transition};
  display: inline-block;
  text-align: center;

  & label {
    display: inline-block;
    width: 30%;
    text-align: left;
  }
  & label + input {
    width: 70%;
  }
`;

export const PasswordSelector = styled.span`
  display: block;
  padding-bottom: 15px;
  color: ${COLOR_SCHEME.secondary};

  & + div {
    opacity: 0;
    display: none;
  }
  &.active + div {
    opacity: 1;
    display: block;
  }
`;
