import styled from 'styled-components';

import { COLOR_SCHEME } from '@styles/colors';

export const ReviewItemWrapper = styled.div`
  border-bottom: 1px solid ${COLOR_SCHEME.lightGray};
  padding: 10px;
`;

export const ReviewInfo = styled.div`
  width: 25%;
  display: inline-block;
  vertical-align: middle;

  & span {
    font-size: 80%;
    color: ${COLOR_SCHEME.darkGray};
  }
`;

export const ReviewContents = styled.div`
  width: 75%;
  display: inline-block;
  vertical-align: middle;
`;
