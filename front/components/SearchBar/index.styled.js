import styled from 'styled-components';

import device from '@styles/device';

export const SearchForm = styled.form`
  ${device.laptops`
    max-width: 60%;
  `}
  ${device.tablets`
    max-width: 80%;
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
  & button {
    width: 20%;
    padding: 5px 10px;
    border-top-left-radius: 0;
    border-bottom-left-radius: 0;
  }
`;
