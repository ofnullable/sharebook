import styled from 'styled-components';

import device from '@styles/device';
import { COLOR_SCHEME } from '@styles/colors';

export const StarWrapper = styled.p`
  margin: 0;
`;

export const FilledStar = styled.i`
  ${device.mobiles`
    font-size: 20px;
  `};
  color: ${COLOR_SCHEME.secondary};
  &[id] {
    cursor: pointer;
  }
`;

export const EmptyStar = styled.i`
  ${device.mobiles`
    font-size: 20px;
  `};
  color: ${COLOR_SCHEME.darkGray};
  &[id] {
    cursor: pointer;
  }
`;
