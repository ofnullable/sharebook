import styled from 'styled-components';

import device from '@styles/device';
import { COLOR_SCHEME } from '@styles/colors';

export const ReviewWrapper = styled.section`
  ${device.laptops`
    padding: 70px 20px 0;
  `};
  ${device.tablets`
    padding: 70px 20px 0;
  `};
  ${device.mobiles`
    width: 100%;
  `};
`;

export const ReviewTitle = styled.h3`
  padding-bottom: 10px;
  border-bottom: 2px solid ${COLOR_SCHEME.gray};
  margin: 1em 0 0;
`;

export const EmptyReview = styled.p`
  padding: 15px;
  margin: 0;
  text-align: center;
`;
