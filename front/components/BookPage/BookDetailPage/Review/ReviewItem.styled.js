import styled from 'styled-components';

import device from '@styles/device';
import { COLOR_SCHEME } from '@styles/colors';
import { Button } from '@styles/common';

export const ReviewItemWrapper = styled.div`
  border-bottom: 1px solid ${COLOR_SCHEME.lightGray};
  padding: 10px;
`;

export const ReviewInfo = styled.div`
  ${device.laptops`
    width: 25%;
    padding: 10px 15px;
  `};
  ${device.tablets`
    width: 25%;
    padding: 10px  15px;
  `};
  ${device.mobiles`
    width: 100%;
    padding: 5px 0;
  `};
  display: inline-block;
  vertical-align: middle;

  & p.reviewer {
    margin: 0.5rem;
    ${device.mobiles`
      margin: 0;
    `};
  }
  & span {
    margin: 0.5rem;
    ${device.mobiles`
      margin: 0;
    `};
    font-size: 80%;
    color: ${COLOR_SCHEME.darkGray};
  }
`;

export const ReviewContents = styled.div`
  ${device.laptops`
    width: 75%;
    padding: 15px 0;
  `};
  ${device.tablets`
    width: 75%;
    padding: 15px 0;
  `};
  ${device.mobiles`
    width: 100%;
    padding: 5px 0;
  `};
  display: inline-block;
  vertical-align: middle;

  & button {
    &:first-child {
      margin-right: 0.5em;
    }
    & i {
      padding-right: 5px;
    }
  }

  & div:last-child {
    text-align: right;
  }
`;

export const DangerButton = styled(Button)`
  color: ${COLOR_SCHEME.red};
`;

export const WarningButton = styled(Button)`
  color: ${COLOR_SCHEME.secondary};
`;
