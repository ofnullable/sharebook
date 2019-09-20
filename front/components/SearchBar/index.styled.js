import styled from 'styled-components';

import device from '@styles/device';
import { Button } from '@styles/common';

export const SearchForm = styled.form`
  ${device.laptops`
    max-width: 60%;
  `}
  ${device.tablets`
    max-width: calc(720px - 2%);
  `}
  ${device.mobiles`
    width: 100%;
  `}
  margin: 1em auto;
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
