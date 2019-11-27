import styled from 'styled-components';

import device from '@styles/device';
import { transition } from '@styles/mixins';
import { COLOR_SCHEME } from '@styles/colors';

export const EditProfileFormWrapper = styled.form`
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

  & button {
    margin-top: 20px;
  }
`;

export const ProfileImage = styled.div`
  position: relative;
  cursor: pointer;
  width: 200px;
  height: 200px;
  margin: 0 auto 15px;
  display: flex;
  align-items: center;
  justify-content: center;
  border-radius: 50%;
  background: ${props => `url(${props._preview}) center center / cover no-repeat`};

  & i {
    position: absolute;
    top: 0;
    right: 0;
    cursor: pointer;
  }
  & span {
    color: ${COLOR_SCHEME.primary};
  }
`;

export const ChangePasswordButton = styled.span`
  cursor: pointer;
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
