import styled, { createGlobalStyle } from 'styled-components';
import device from './device';
import { transition } from './mixins';
import { normalize } from './nomalize';
import { COLOR_SCHEME } from './colors';

const buttonColor = props => `
  color: ${COLOR_SCHEME.white};
  background-color: ${COLOR_SCHEME[`${props._color}`]};
`;

export const Button = styled.button`
  ${transition};
  ${buttonColor};
  cursor: pointer;
  display: inline-block;
  padding: 5px 15px;
  border: 1px solid;
  border-radius: 5px;
  text-align: center;
`;

export const CenterAligned = styled.div`
  text-align: center;
`;

export const RightAligned = styled.div`
  text-align: right;
`;

export const LeftAligned = styled.div`
  text-align: left;
`;

export const InputGroup = styled.div`
  margin: 15px 0;
`;

export const GlobalStyle = createGlobalStyle`
  ${normalize}

  .container {
    ${device.laptops`
      max-width: 1140px;
      margin: 0 auto;
    `}
    ${device.mobiles`
      padding:15px;
    `}
  }

  ul {
    padding: 0;
  }

  h1 {
    ${device.laptops`
      font-size: 2em;
    `};
    ${device.mobiles`
      font-size: 1.5em;
    `};
  }

  form {
    text-align: left;
  }

  input {
    width: 100%;
    padding: 5px 10px;
    border-radius: 5px;
    border: 1px solid ${COLOR_SCHEME.gray};
    &:read-only {
      background: ${COLOR_SCHEME.lightGray};
    }
  }
`;
