import styled, { createGlobalStyle, keyframes } from 'styled-components';
import device from './device';
import { transition } from './mixins';
import { normalize } from './nomalize';
import { COLOR_SCHEME } from './colors';

const buttonColor = props => `
  color: ${props._color ? COLOR_SCHEME.white : COLOR_SCHEME.primary};
  border: 1px solid ${COLOR_SCHEME[`${props._color}`] || 'white'};
  background-color: ${COLOR_SCHEME[`${props._color}`] || 'white'};
`;

export const Button = styled.button`
  ${transition};
  ${buttonColor};
  cursor: pointer;
  display: inline-block;
  padding: 3px 15px;
  border-radius: 5px;
  text-align: center;
`;

export const ButtonLink = Button.withComponent('a');

const spin = keyframes`
  from {
    transform: rotate(0deg);
  }
  to {
    transform: rotate(359deg);
  }
`;

export const SpinIcon = styled.i`
  animation: ${spin} 1s infinite linear;
  font-size: ${props => props._size};
  color: ${props => COLOR_SCHEME[`${props._color}`] || 'inherit'};
  vertical-align: middle;
`;

export const CenterDiv = styled.div`
  padding-top: 15px;
  text-align: center;
`;

export const RightDiv = styled.div`
  padding-top: 15px;
  text-align: right;
`;

export const LeftDiv = styled.div`
  padding-top: 15px;
  text-align: left;
`;

export const CenterForm = styled.form`
  ${device.laptops`
    max-width: 500px;
  `}
  ${device.tablets`
    max-width: 350px;
  `}
  ${device.mobiles`
    width: 100%;
  `}
  margin: 0 auto;
  overflow: auto;
`;

export const InputGroup = styled.div`
  padding-bottom: 15px;
  & > label .import {
    color: ${COLOR_SCHEME.red};
  }
`;

export const GlobalStyle = createGlobalStyle`
  ${normalize}
  html {
    ${device.laptops`
      font-size: 16px;
    `};
    ${device.tablets`
      font-size: 16px; 
    `};
    ${device.mobiles`
      font-size: 14px;
    `};
  }
  .container {
    ${device.laptops`
      max-width: 1080px;
      margin: 0 auto;
    `}
    ${device.tablets`
      padding: 0 15px;
    `}
    ${device.mobiles`
      padding: 0 20px;
    `}
  }
  ul {
    padding: 0;
  }
  h1 {
    ${device.laptops`
      font-size: 2em;
    `};
    ${device.tablets`
      font-size: 1.7em;
    `};
    ${device.mobiles`
      font-size: 1.5em;
    `};
    &.title {
      ${device.laptops`
        font-size: 3em;
      `};
      ${device.tablets`
        font-size: 2.5em;
      `};
      width: 100%;
      margin: 0;
      padding-top: 1em;
      text-align: center;
    }
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
