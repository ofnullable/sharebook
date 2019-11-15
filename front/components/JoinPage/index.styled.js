import styled from 'styled-components';

import { COLOR_SCHEME } from '@styles/colors';

export const SuccessText = styled.p`
  margin: 0;
  padding-bottom: 15px;
  text-align: center;
  color: ${COLOR_SCHEME.primary};
`;

export const ErrorText = styled(SuccessText)`
  color: ${COLOR_SCHEME.red};
`;
