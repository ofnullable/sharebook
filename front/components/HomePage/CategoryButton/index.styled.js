import styled from 'styled-components';

import { COLOR_SCHEME } from '@styles/colors';
import { Button } from '@styles/common';

export const Category = styled(Button)`
  padding: 7px;
  margin: 0.3rem 0.3rem 0 0;
  line-height: 1;
  color: ${COLOR_SCHEME.white};
  background-color: ${COLOR_SCHEME.secondary};
  vertical-align: bottom;
`;
