import styled from 'styled-components';

import device from '@styles/device';
import { COLOR_SCHEME } from '@styles/colors';

export const ReviewItemWrapper = styled.div`
  border-bottom: 1px solid ${COLOR_SCHEME.lightGray};
  padding: 15px;
`;

export const ReviewInfo = styled.div`
  ${device.laptops`
    width: 25%;
    padding: 0 15px;
  `};
  ${device.tablets`
    width: 25%;
    padding: 0 15px;
  `};
  ${device.mobiles`
    width: 100%;
    padding: 5px 0 10px;
  `};
  display: inline-block;
  vertical-align: middle;

  & p {
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
  `};
  ${device.tablets`
    width: 75%;
  `};
  ${device.mobiles`
    width: 100%;
  `};

  display: inline-block;
  vertical-align: middle;
`;
