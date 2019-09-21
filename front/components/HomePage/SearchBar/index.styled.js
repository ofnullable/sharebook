import styled from 'styled-components';

import { Button } from '@styles/common';

export const SearchForm = styled.form`
  & input {
    width: 80%;
    border-top-right-radius: 0;
    border-bottom-right-radius: 0;
  }
`;

export const SearchButton = styled(Button)`
  width: 20%;
  padding: 5px 10px;
  border-top-left-radius: 0;
  border-bottom-left-radius: 0;
`;
