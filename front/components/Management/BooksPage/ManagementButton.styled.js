import styled from 'styled-components';

import { COLOR_SCHEME } from '@styles/colors';

export const ManagementButtonWrapper = styled.div`
  width: 100%;
  padding-top: 5px;

  & button {
    width: 50%;
  }
  & .reject {
    color: ${COLOR_SCHEME.red};
  }
  & .return {
    width: 100%;
  }
`;
