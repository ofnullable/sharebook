import styled from 'styled-components';

import { COLOR_SCHEME } from '@styles/colors';

export const ReviewTitle = styled.h3`
  padding-bottom: 10px;
  border-bottom: 2px solid ${COLOR_SCHEME.gray};
  margin: 1em 0;
`;

export const EmptyReview = styled.p`
  padding: 15px;
  margin: 0;
  text-align: center;
`;
