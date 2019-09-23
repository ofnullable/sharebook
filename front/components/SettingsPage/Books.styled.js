import styled from 'styled-components';

import { Button } from '@styles/common';

export const HeaderWrapper = styled.div`
  &::after,
  &::before {
    content: '';
    clear: both;
    display: block;
  }
`;

export const RegisterButton = styled(Button)`
  float: right;
  margin: calc(0.7rem - 2px) 1%;
`;
